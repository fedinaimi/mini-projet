const express = require("express");
const multer = require("../middlewares/multer-config");

const {addproduit,allproduct,getbyid} = require("../controllers/produit");
const { route } = require("./reels");

const router = express.Router();

router.post("/addproduit",multer.single('image') ,addproduit);

router.get("/allproduct",allproduct)
router.get("/:id",getbyid)

module.exports = router;
