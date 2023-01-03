const express = require("express");
const { addpanier,getpanier } = require("../controllers/panier");

const router = express.Router();

router.post("/addpanier", addpanier);
router.get("/:client",getpanier)

module.exports = router;
