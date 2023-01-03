const produit = require("../models/produit");

//--------------------------ajouter une categorie ---------------------------//
exports.addproduit = (req, res) => {
  let newproduit = new produit({ ...req.body });

  // Initialize newUser object with request data

  newproduit
    .save()

    .then(
      res.status(200).json({
        message: "add product with success",
      })
    )
    .catch(
      res.status(400).json({
        error: "unable to add product",
      })
    );
};
