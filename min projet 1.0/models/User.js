const mongoose = require("mongoose");
const options = { discriminatorKey: "usertype" };
const crypto = require("crypto");
const { func, boolean } = require("joi");

const UserSchema = new mongoose.Schema(
  {
 
    firstName: {
      type: String,
      required: true,
    },
    lastName: {
      type: String,
      required: true,
    },
    email: {
      type: String,
      required: true,
    },
    userType: {
      type: String,
      
    },
    created_at: {
      type: Date,
      default: Date.now(),
    },
    verified: {
      type: Boolean,
      default: false,
    },

   
    adresse:{
     type:String},

     mobilenumber:{
       type:String,},
       
    hash: String,
    salt: String,
  },

  options
);

UserSchema.methods.setPassword = function (password) {
  // Creating a unique salt for a particular user
  this.salt = crypto.randomBytes(16).toString("hex");

  // Hashing user's salt and password with 1000 iterations,

  this.hash = crypto
    .pbkdf2Sync(password, this.salt, 1000, 64, `sha512`)
    .toString(`hex`);
};


// Method to check the entered password is correct or not
UserSchema.methods.validPassword = function (password) {
  var hash = crypto
    .pbkdf2Sync(password, this.salt, 1000, 64, `sha512`)
    .toString(`hex`);
  return this.hash === hash;
};
UserSchema.methods.generatePassword = function(password){
  (length = 20,
  wishlist = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~!@-#$'
) =>
  Array.from(crypto.randomFillSync(new Uint32Array(length)))
    .map((x) => wishlist[x % wishlist.length])
    .join('')
}
module.exports = mongoose.model("User", UserSchema);
