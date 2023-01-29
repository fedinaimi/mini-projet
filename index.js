const io = require("socket.io")(3000, {
    cors: {
        origin: "*",
    },
});

let activeUsers = [];

io.on("connection", (socket) => {
    // add new User
    socket.on("new-user-add", (new_id) => {
        // if user is not added previously
        if (!activeUsers.some((user) => user._id === new_id)) {
            activeUsers.push({ _id: new_id, socketId: socket.id });
            console.log("New User Connected", activeUsers);
        }
        // send all active users to new user
        io.emit("get-users", activeUsers);
    });

    socket.on("disconnect", () => {
        // remove user from active users
        activeUsers = activeUsers.filter((user) => user.socketId !== socket.id);
        console.log("User Disconnected", activeUsers);
        // send all active users to all users
        io.emit("get-users", activeUsers);
    });

    // send message to a specific user
    socket.on("send-message", (data) => {
        const { receiverId } = data;
        const user = activeUsers.find((user) => user._id === receiverId);
        console.log("Sending from socket to :", receiverId)
        console.log("Data: ", data)

        io.emit("recieve-message", data);

    });
});
