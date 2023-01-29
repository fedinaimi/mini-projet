const { number, required } = require("joi");
const mongoose = require("mongoose");

const panierSchema = new mongoose.Schema({
 Prduit:{
  type: String,
    required: true,
 },

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
  quantiteProduit: {
    type:Number ,
    
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
