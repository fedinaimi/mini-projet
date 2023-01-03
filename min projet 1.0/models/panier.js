const { number, required } = require("joi");
const mongoose = require("mongoose");
const produit = require("./produit");

const panierSchema = new mongoose.Schema({
  nom: {
    type: String,
    required: false,
  },
  image: {
    type: String,
    required: false,
  },
  prix: {
    type:String ,
    
    required: false,
  },
  client: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "user",
    required: true,
  },
  Totale : {
    type : String,
    required : false 
  }
});

module.exports = mongoose.model("panier", panierSchema);
