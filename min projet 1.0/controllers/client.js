const Client = require("../models/client");
const toClient = require("./user")
const User =require("../models/User")


exports.addClient = async (req, res) => {
  
  try {
    let NewClient = await Client.findOne(
      ({ firstName, lastName, email, password, adresse,infolivraison} = req.body)
    );
    if (NewClient)
      return res
        .status(409)
        .send({ message: "Client with given email already Exist!" });

    /*const salt = await bcrypt.genSalt(Number(process.env.SALT));
    const hashPassword = await bcrypt.hash(req.body.password, salt);*/

    NewClient = new Client({ ...req.body });
    NewClient.setPassword (req.body.password);  

    await NewClient.save();
  
  } catch (error) {
    console.log(error);
    res.status(500).send({ message: "Internal Server Error" });
  }
};
