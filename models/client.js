

const mongoose = require("mongoose");
var crypto = require("crypto");
const user = require("./User");
const ClientSchema = new mongoose.Schema({
    adresse: {
        type: String,
        required: true,
      },
      infolivraison: {
        type: String,
        required: true,
      },
  
}); 

var Client = user.discriminator("Client", ClientSchema);

if (mongoose.models.Client) {
    Client = mongoose.model("Client");
} else {
    Client = mongoose.model("Client", ClientSchema);
}

module.exports = Client;
