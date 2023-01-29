const { number } = require("joi");
const mongoose = require("mongoose");
const boutique = require("./boutique");

const produitSchema = new mongoose.Schema({


      produit: {
              type:String,
             required : false,
            },
            quantite: {
              type: Number,
             
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
            etat: {
              type:String,
             required : false,
            },
            image:{
              type:String,
              required : false,

            },
            created_at: {
              type: Date,
              default: Date.now(),
            },
            category:{
              type:String,
              required : false,
            },
            client: {
                type:mongoose.Schema.Types.ObjectId,
                ref: "User",
                required: true,
              },
           boutique: {
                type: mongoose.Schema.Types.ObjectId,
                ref: "boutique",
                required: true,
              },

          },
      
    );

module.exports = mongoose.model("produit", produitSchema);
