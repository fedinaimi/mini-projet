const panier = require("../models/panier");

//--------------------------ajouter une categorie ---------------------------//
exports.addpanier= (req, res) => {
    
    let newpanier = new panier({...req.body});
    
    // Initialize newUser object with request data

    
    newpanier.save((err, newpanier) => {
        if (err) {
            
          return res.status(400).json({
            error: "unable to add product",
          });
        }
        return res.json({
          message: "sucsess",
          newpanier,
        });
      });
  };