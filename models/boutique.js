const { number } = require("joi");
const mongoose = require("mongoose");

const boutiqueSchema = new mongoose.Schema({
    nom: {
        type: String,
        required: true,
      },
      adresse:{
        type: String,
        required: true,
    },
    
    linkSocialmedia:{
    
        type: String,
        required: false,
      },
      brandemail:{
    
        type: String,
        required: false,
      },
      numtelf:{
    
        type: String,
        required: false,
      },
      Latitude:{
    
        type: Number,
        required: false,
      },
      Longitude:{
    
        type: Number,
        required: false,
      },
      client: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "User",
        required: true,
      },
  
      produits: [{
        type: mongoose.Schema.Types.ObjectId,
        ref: "produit",
        required: true
      }],
    
});

module.exports = mongoose.model("boutique", boutiqueSchema);
