const panier = require("../models/panier");
const ObjectId = require("mongodb").ObjectId;
let totale = 0;

exports.addpanier = (req, res) => {
  let newpanier = new panier({ ...req.body });

  newpanier.save((erro, newpanier) => {
    if (erro) {
      return res.status(400).json({
        error: "unable to add product",
      });
    }
    return res.json({
      message: "sucsess",
      newpanier,
    });
  });
  console.log(newpanier);
};
exports.getpanier= async (req, res) => {
  
 console.log(totale)
  try {
   const cart = await panier.find({ client: req.params.client },
    
  )

   res.json(cart)
  } catch (error) {
   res.status(400).json(error)
  }
};