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
      client: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "user",
        required: true,
      },
      produits: {
        type: [
          {
            produit: {
              type:String,
             required : false,
            },
            quantite: {
              type: Number,
              default: 0,
              required: false,
            },
            prix: {
              type:Number ,
              required: false ,
            },
            description: {
              type:String,
             required : false,
            },
            image:{
              type:String,
              required : false,

            }
          

          },
        ],
        required: false,
      },
    
});

module.exports = mongoose.model("boutique", boutiqueSchema);
