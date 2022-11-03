const mongoose = require("mongoose");

const Schema = mongoose.Schema;

const tokenSchema = new Schema({
  userId: {
    type: Schema.Types.ObjectId,
    require: true,
    ref: "user",
    unique: true,
  },
  token: { type: String, require: true },
  ccreated_at: {
    type: Date,
    default: Date.now(),
    //expires: 3600,
  },
});
module.exports = mongoose.model("token", tokenSchema);
