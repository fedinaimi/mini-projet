const express = require("express");
const {
    addproduit,
 
} = require("../controllers/produit");

const router = express.Router();

router.post("/addproduit", addproduit);


module.exports = router;
