const { number, required } = require("joi");
const mongoose = require("mongoose");


const reelsSchema = new mongoose.Schema({

    client: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "User",
    required: true,
  },

 
  video : {
    type: String,
    required: false,
  },
});

module.exports = mongoose.model("reels", reelsSchema);
