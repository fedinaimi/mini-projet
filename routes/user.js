const express = require("express")
const userController=require("../controllers/user")
const multer = require('../middlewares/multer-config')
const { check } = require("express-validator");
const { sign } = require("crypto");
const auth = require("../middlewares/auth");
const router = express.Router();
const{
signup,
signin,
signout,
profile,
  Token,
  forgotPassword,
  resetPassword,
  

}=require("../controllers/user");

router.post("/signup", userController.signup);
router.post("/signin", userController.signin);
//router.post("/profile");
//router.get("/signout", userController.signout);
//router.get("/", auth, userController.getUsers);
router.get("/:id/verify/:token", Token);
//router.post('/forgot-password',forgotPassword);
//router.post('/:id/reset-password/:token',resetPassword);
//router.put("/updateProfile/:id", updateProfile);
module.exports = router ;