const mongoose = require("mongoose");

const commandeSchema = new mongoose.Schema({
  status: {
    type: String,
    required: true,
  },
  client: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "Client",
  },
  dateCreation: {
    type: Date,
    required: true,
  },
  dateEnvoi: {
    type: Date,
    required: true,
  },
});

module.exports = mongoose.model("commande", commandeSchema);
