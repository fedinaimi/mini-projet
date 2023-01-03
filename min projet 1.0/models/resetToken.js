const mongoose = require("mongoose");

const Schema = mongoose.Schema;

const resetToken = new Schema({
  userId:{
  type:Schema.Types.ObjectId,
  require:true,
  ref: "user",
  unique: true,
},
token:{type:String , require : true},
ccreated_at: {
    type: Date,
    default: Date.now(),
   
},

});
module.exports = mongoose.model("resetToken",resetToken);
