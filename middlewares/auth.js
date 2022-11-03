const jwt = require("jsonwebtoken");

module.exports = (req, res, next) => {
  try {
    const token = req.headers.authorization.split(" ")[1];
    const decodedToken = jwt.verify(token, "RESTFULAPIs");
    const userId = decodedToken._id;
    req.auth = { userId };
    if (req.body._id && req.body._id !== userId) {
      throw "Invalid participant ID";
    } else {
      next();
    }
  } catch (error) {
    res.status(401).json({ message: "can't authentify request" });
  }
};
