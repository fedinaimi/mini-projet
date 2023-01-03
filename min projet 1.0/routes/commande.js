const express = require("express");
const { addCommande } = require("../controllers/commande");

const router = express.Router();

router.post("/addcommande", addCommande);

module.exports = router;
