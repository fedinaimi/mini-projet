const { number } = require("joi");
const mongoose = require("mongoose");
const produit = require("./produit");

const panierSchema = new mongoose.Schema({
 
  numeroPanier: {
    type: String,
    required: true,
  },
 produit:{
    type: mongoose.Schema.Types.ObjectId,
    ref:"produit",
 },
 quantite:{
    type: Number,
    required : true, 

 },
 totale:{
    type: mongoose.Types.Decimal128,
    default : 0,
    required : true ,
 },
 client: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "client",
    required: false,
  },


  
});



    
    

module.exports = mongoose.model("panier", panierSchema);
