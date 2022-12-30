const boutique = require("../models/boutique");

const ObjectId = require("mongodb").ObjectId;


exports.addboutique = (req, res) => {
  let newboutique = new boutique({ ...req.body });

  newboutique.save((erro, newboutique) => {
    if (erro) {
      return res.status(400).json({
        error: "unable to add product",
      });
    }
    return res.json({
      message: "sucsess",
      newboutique,
    });
  });
  console.log(newboutique);
};
exports.allboutique = async (req, res) => {
  try {
    const bou = await boutique.find()
    res.json(bou)

  } catch (error) {
    res.status(500).json(error)
  }
}

exports.update = (req, res) => {


  console.log("haniji")
  console.log(req.body)
  console.log(req.params)
  console.log(req.file)



   boutique
    .findByIdAndUpdate(
      { _id: req.params.id },
      {$push:{
        produits:{...req.body,
          image:req.file.filename
        },
        
    

      
     

      },
      

     

    }
      //{ ...req.body, _id: req.params.id }
    )
    .then(() => res.status(200).json({ message: "boutique modifie" }))
    .catch((error) => res.status(400).json({ error }));
};


exports.getOneBoutique = async (req, res) => {
 
   try {
    const bou = await boutique.findById({ _id: req.params.id })
    res.status(200).json(bou)
   } catch (error) {
    res.status(400).json(error)
   }
};