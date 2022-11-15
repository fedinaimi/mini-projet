const panier = require("../models/panier");
const { collection } = require("../models/produit");
const produit = require("../models/produit");
const ObjectId = require("mongodb").ObjectId;
let totale = 0;

exports.addpanier = (req, res) => {
  let newpanier = new panier({ ...req.body });
  req.body.produits.forEach((prod) =>
    produit
      .findById(ObjectId(prod.produit))
      .then((pr) => {
        totale = totale + pr.prix * prod.quantite;
        newpanier.totale = totale;
      })
      .catch((err) => {
        console.log(err);
      })
  );
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
