const User = require("../models/User");
const bcrypt = require("bcryptjs");
const crypto = require("crypto");
var jwt = require("jsonwebtoken");
var expressJwt = require("express-jwt");
const { body } = require("express-validator");
const Token = require("../models/Token");
const urll = "http://172.16.2.67:5000/api";
var aes256 = require('aes256');
const resetToken = require("../models/resetToken");

const sendEmail = require("../controllers/sendEmail");
const resetPassword = require("../controllers/resetPassword");
const { url } = require("inspector");
const Joi = require("joi");

const router = require("../routes/user");
//signup
exports.signup=async(req , res)=>{
try{
    let user = await User.findOne(
        ({firstName, lastName, email, password}=req.body)
    );
    if(user)
    return res
    .status(409)
    .send({message:"user with given email already exist"});
    user = new User({...req.body});
    user.setPassword(req.body.password);
    await user.save();
    const token = await new Token({
        userId: user._id,
        token: crypto.randomBytes(32).toString("hex"),
    }).save();
    const url =`${urll}/${user._id}/verify/${token.token}`;
    await sendEmail(user.email, "Verify Email", url);
    res
      .status(201)
      .send({ message: "An Email sent to your account please verify" });
  } catch (error) {
    console.log(error);
    res.status(500).send({ message: "Internal Server Error" });
  }
};
//verify with link 
exports.Token = async (req, res) => {
    // Find a matching token
    Token.findOne({ token: req.params.token }, function (err, token) {
      if (!token)
        return res.status(400).send({
          type: "not-verified",
          msg: `We were unable to find a valid token.Your token my have expired.`,
        });
  
      // If we found a token, find a matching user
      User.findOne({ _id: token.userId }, function (err, user) {
        if (!user)
          return res
            .status(400)
            .send({ msg: "We were unable to find a user for this token." });
        if (user.verified)
          return res.status(400).send({
            type: "already-verified",
            msg: "This user has already been verified.",
          });
  
        // Verify and save the user
        user.verified = true;
        user.save(function (err) {
          if (err) {
            return res.status(500).send({ msg: err.message });
          }
          res.status(200).send("The account has been verified. Please login.");
        });
      });
    });
  };
  //signin
  exports.signin = (req, res) => {
    User.findOne({ email: req.body.email }, function (err, user) {
      if (user === null) {
        return res.status(400).send({
          message: "User not found.",
        });
      } else {
        if (user.validPassword(req.body.password) && user.verified == true || Admin.verified == true) {
          return res.json({
            token: jwt.sign(
              { email: user.email, firstName: user.firstName, _id: user._id },
              "RESTFULAPIs"
            ),
            user,
          });
        } else if (
          user.validPassword(req.body.password) &&
          user.verified == false
        ) {
          return res.status(400).send({
            message: "user not verified",
          });
        } else {
          return res.status(400).send({
            message: "Wrong Password or user not verified",
          });
        }
      }
    });
  };
  //------------------------------forgot password------------------//
exports.forgotPassword = async (req, res) => {
  try {
    let user = await User.findOne({ email: req.body.email });
    if (!user)
      return res
        .status(409)
        .send({ message: "User with given email not Exist!" });

    /*const salt = await bcrypt.genSalt(Number(process.env.SALT));
    const hashPassword = await bcrypt.hash(req.body.password, salt);*/
    const token = await new resetToken({
      userId: user._id,
      token: crypto.randomBytes(32).toString("hex"),
    }).save();

    const url = `${urll}/${user._id}/reset-password/${token.token}`;
    await resetPassword(user.email, "reset Password Email", url);
    res
      .status(201)
      .send({ message: "An Email sent to your account please verify" });
  } catch (error) {
    console.log(error);
    res.status(500).send({ message: "Internal Server Error" });
  }
};
//--------------------------reset password-----------------------//
exports.resetPassword = async (req, res) => {
  resetToken.findOne({ token: req.params.token }, function (err, token) {
    if (!token)
      return res.status(400).send({
        type: "not-exist",
        msg: `We were unable to find a valid token.Your token my have expired.`,
      });

    // If we found a token, find a matching user
    User.findOne({ _id: token.userId }, function (err, user) {
      if (!user)
        return res
          .status(400)
          .send({ msg: "We were unable to find a user for this token." });

      // Verify and save the user
      user.password = req.body.password;
      user.setPassword(req.body.password);

      user.save(function (err) {
        if (err) {
          return res.status(500).send({ msg: err.message });
        }
        res.status(200).send("The password has been changed. Please login.");
      });
    });
  });
};
//--------------------------signout---------------------------//
exports.signout = (req, res) => {
  res.clearCookie("token");
  return res.json({
    message: "user signout",
  });
};
//-------------------------makeAdmin---------------------------//

exports.makeAdmin = (req, res) => {
  User.findOneAndUpdate(
    { _id: req.params.id },
    { $set: { userType: "Admin"} },
    { new: true, upsert: false }
  )
    .then((users) => {
      res.status(200).json({ users , message: "changed !"});
      console.log(user.userType)
    })
    .catch((error) => {
      res.satus(400).json({ error, message: "faild" } );
    });
};
exports.profile = async (req,res)=>{
  try{
    const user = await User.findById({_id:req.params.id});
    if(!user){
      return res.json({message:'No user found'})
    }
    res.json(user)
  }catch(error){
    res.status(500).json(error)
  }
}
exports.getUsers=async (req,res)=>{
  try {const user = await User.find()
  res.json(user)

} catch (error) {
res.status(500).json(error)}
}



/*exports.getUsers=async (req,res)=>{
  try {
    const user  = await User.findOne({ _id: req.params.id });
    if(!user){
        return res.json({message:'No user found'})
    }
    return res.json({user:user})
} catch (error) {
    return res.json({ error: error });  
}}*/