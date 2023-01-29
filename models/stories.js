const { number, required } = require("joi");
const mongoose = require("mongoose");


const storiesrSchema = new mongoose.Schema({


  client: {
  type: mongoose.Schema.Types.ObjectId,
  ref: "User",
  required: true,
},

  image : {
    type : String,
    required : false 
  },
  video : {
    type: String,
    required: false,
  },
});

module.exports = mongoose.model("stories", storiesrSchema);
