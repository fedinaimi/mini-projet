const categorie = require("../models/categorie");

//--------------------------ajouter une categorie ---------------------------//
exports.addcategorie = (req, res) => {
    let newCategorie = new categorie();
  
    // Initialize newUser object with request data
    (newCategorie.nom = req.body.nom),
      (newCategorie.type = req.body.type),
    
      newCategorie.save((err, newCategorie) => {
        if (err) {
          return res.status(400).json({
            error: "unable to add session",
          });
        }
        return res.json({
          message: "sucsess",
          newCategorie,
        });
      });
  };
/*//--------------------------afficher tous les categorie  ---------------------------//
exports.show = (req, res) => {
   categorie
    .find()
    .then((categorie) => {
      res.status(200).json(categorie);
    })
    .catch((error) => {
      res.status(400).json({
        error: "no category",
      });
    });
};
//-------------------------afficher categorie par id -------------------------------------//
exports.details = (req, res) => {
    categorie
    .findOne({
      _id: req.params.id,
    })
    .then((categorie) => {
      res.status(200).json(categorie);
    })
    .catch((error) => {
      res.status(404).json({
        error: error,
      });
    });
};
//---------------------------modifier categorie -------------------------------------------//
exports.update = (req, res) => {
    categorie
    .findByIdAndUpdate(
      { _id: req.params.id },
      { ...req.body, _id: req.params.id }
    )
    .then(() => res.status(200).json({ message: "category modifié !" }))
    .catch((error) => res.status(400).json({ error }));
};
//------------------------------delete categorie---------------------------------------------//
exports.deleted = (req, res) => {
    categorie
    .findByIdAndDelete({ _id: req.params.id })
    .then(() => res.status(200).json({ message: "category supprimé !" }))
    .catch((error) => res.status(400).json({ error }));
};*/
