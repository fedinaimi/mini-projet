const mongoose = require("mongoose");

const produitSchema = new mongoose.Schema({
 
  nom: {
    type: String,
    required: true,
  },
  prix:{
type:mongoose.Types.Decimal128 ,
required: true ,
},

reference:{

    type: String,
    required: true,
  },
  stock:{
    type : Number,
    required : true,
  },
  etat:{
    type : String,
    required : true,
  },
  description: {
    type : String,
    required : true,
  },

  categorie: {
    
    type: mongoose.Schema.Types.ObjectId,
    ref: "Categorie",
  },
 
  
});

module.exports = mongoose.model("produit", produitSchema);
