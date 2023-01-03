const commande = require("../models/commande");

//--------------------------ajouter une categorie ---------------------------//
exports.addCommande = (req, res) => {
  let newCommande = new commande({ ...req.body });

  newCommande.save((err, newCommande) => {
    if (err) {
      console.log(err);

      return res.status(400).json({
        error: "Impossible d'ajouter la commande",
      });
    }
    return res.json({
      message: "sucsess",
      newCommande,
    });
  });
};
