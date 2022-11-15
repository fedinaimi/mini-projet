const produit = require("../models/produit");

//--------------------------ajouter une categorie ---------------------------//
exports.addproduit = (req, res) => {
    let newproduit = new produit({...req.body});
  
    // Initialize newUser object with request data

    
      newproduit.save((err, newproduit) => {
        if (err) {
            
          return res.status(400).json({
            error: "unable to add product",
          });
        }
        return res.json({
          message: "sucsess",
          newproduit,
        });
      });
  };