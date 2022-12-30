const { number } = require("joi");
const mongoose = require("mongoose");


const panierSchema = new mongoose.Schema({
  produits: {
    type: [
      {
        produit: {
          type: mongoose.Schema.Types.ObjectId,
          ref: "boutique",
        },
        quantite: {
          type: Number,
          default: 0,
          required: true,
        },
      },
    ],
    required: false,
  },

  totale: {
    type: mongoose.Types.Decimal128,
    default: 0,
    required: false,
  },
  client: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "client",
    required: false,
  },
});

module.exports = mongoose.model("panier", panierSchema);
