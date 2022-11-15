const { number } = require("joi");
const mongoose = require("mongoose");

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
    required : true ,
 }
  
});

module.exports = mongoose.model("panier", panierSchema);
