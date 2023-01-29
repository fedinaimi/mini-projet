const express = require("express");
const multer = require("../middlewares/multer-config");


const { addreels,allreels} = require("../controllers/reels");

const router = express.Router();

router.post("/addreels",multer.single('video'), addreels);

router.get("/allreels", allreels);



module.exports = router;
