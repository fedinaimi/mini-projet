const boutique = require("../models/boutique");
const produit = require("../models/produit");
const ObjectId = require("mongodb").ObjectId;


exports.addboutique = (req, res) => {
  let newboutique = new boutique({ ...req.body });

  newboutique.save((erro, newboutique) => {
    if (erro) {
      return res.status(400).json({
        error: "unable to add brand",
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
    .populate("produits")
    res.json(bou)

  } catch (error) {
    res.status(500).json(error)
  }
}
exports.myboutique = async (req, res) => {
  try {
    const bou = await boutique.findById({client:req.params.client})
    
    res.json(bou)

  } catch (error) {
    res.status(500).json(error)
  }
}

exports.update =async (req, res) => {


  console.log("haniji")
  console.log(req.body)
  console.log(req.params)
  console.log(req.file)
const prod = {...req.body,image:req.file.filename}

const newProduit = await produit.create(prod)
   boutique
   
    .findByIdAndUpdate(
      { _id: req.params.id },
     {$push: {produits:
      newProduit._id
    
    },},
    {new : true , useFindAndModify:false},
      //{ ...req.body, _id: req.params.id }
    )
    .then(() => res.status(200).json({ message: "boutique modifie" }))
    .catch((error) => res.status(400).json({ error }));
}


exports.getOneBoutique = async (req, res) => {
 
   try {
    const bou = await boutique.findById({ _id: req.params.id })
    .populate("produits")
    res.status(200).json(bou)
   } catch (error) {
    res.status(400).json(error)
   }
};

exports.mybusiness = async(req,res)=>{
  try{
    const bou = await boutique.find({client:req.params.client})
    res.json(bou)
  }catch(error){
    res.status(400).json(error)
  }
};
exports.deleteProduit = async(req,res)=>{
  try{
    const prod = {...req.body}

    const Produit = await produit.deleteOne(prod)
    const bou = await boutique.findOneAndUpdate({_id:req.params.id},
      {
        $pull:{
          produits:
         Produit._id
        },
      })
    res.status(200).json(bou)

  }catch(error){
    res.status(400).json(error)
  }
};

  exports.allproduct=async (req, res) => {
    try {
      
      const bou = await boutique.find({})
      return res.json(bou)
  
    } catch (error) {
      res.status(500).json(error)
    }
  }
