const express = require("express");
const { addpanier } = require("../controllers/panier");

const router = express.Router();

router.post("/addpanier", addpanier);

module.exports = router;
