const express = require("express");
const multer = require("../middlewares/multer-config");


const { addstories,allstories} = require("../controllers/stories");

const router = express.Router();

router.post("/addstories",multer.single('image'), addstories);

router.get("/allstories", allstories);



module.exports = router;
