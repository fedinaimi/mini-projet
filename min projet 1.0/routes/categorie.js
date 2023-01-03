const express = require("express");
const {
    addcategorie,
 
} = require("../controllers/categorie");
const { check } = require("express-validator");
const { sign } = require("crypto");
const router = express.Router();

router.post("/addcategorie", addcategorie);


module.exports = router;
