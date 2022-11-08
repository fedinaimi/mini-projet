const Admin = require("../models/admin");
const toAdmin = require("./user")
const User =require("../models/User")


exports.addAdmin = (req, res) => {
  newAdmin.save()
    .then(() => {
      toAdmin.makeAdmin()
      (res.status(200).json({ message: "admin crated !" }))})

    .catch((error) => res.status(400).json({ error }))
  }

    /*

  let newAdmin = new Admin({
    ...req.body,
  });

  newAdmin.save()

    .then((admin) => {
      toAdmin.makeAdmin()
      res.status(201).json( admin ,{ message: "Admin created" })})
    .catch((error) => res.status(400).json({ error, message :"faild ..."}));
    */


exports.getAllAdmins = (req, res) => {
  Admin.find()
    .then((admin) => {
      res.status(200).json({ massage: "sucsses", admin });
    })
    .catch((error) => {
      res.status(400).json({
        error: "Admin dosen't exist",
      });
    });
};
exports.getAdminById = (req, res) => {
  Admin.findById({
    _id: req.params.id,
  })
    .then((admin) => {
      res.status(200).json({ message: "sucess", admin });
    })
    .catch((error) => {
      res.status(404).json({
        error: error,
      });
    });
};
exports.getAdminByEmail = (req, res) => {
  Admin.findOne({ _email: req.params.email }).then((admin) => {
    if (!admin) {
      return res.status(400).send("admin not found");
    } else
      return res.status(200).send({ message: "admin found", value: admin });
  });
};
exports.update = (req, res) => {
  Admin.findOne({ _id: req.params.id }, { ...req.body, _id: req.params.id })
    .then(() => res.status(200).json({ message: "admin modified " }))
    .catch((error) => res.status(400).json({ error }));
};

exports.deleted = (req, res) => {
  Admin.findByIdAndDelete({ _id: req.params.id })
    .then(() => res.status(200).json({ message: "Admin deleted " }))
    .catch((error) => res.status(400).json({ error }));
};