const boutique = require("../models/boutique");
const produit = require("../models/produit");
const ObjectId = require("mongodb").ObjectId;


exports.addproduit = (req, res) => {
  let newProduit = new produit({ ...req.body ,image:req.file.filename});

  newProduit.save((erro, newProduit) => {
    if (erro) {
      return res.status(400).json({
        error: "unable to add brand",
      });
    }
    return res.json({
      message: "sucsess",
      newProduit,
    });
  });
  console.log(newProduit);
};
exports.allproduct = async (req, res) => {
  try {
    const bou = await produit.find()
    res.json(bou)

  } catch (error) {
    res.status(500).json(error)
  }
}

  exports.getbyid = async (req, res) => {
 
    try {
     const bou = await produit.findById({ _id: req.params.id })
     
     res.status(200).json(bou)
    } catch (error) {
     res.status(400).json(error)
    }
  };