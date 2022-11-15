const express = require("express");
const { check } = require("express-validator");
const router = express.Router();
const clientControleur = require("../controllers/client");

router.post("/addClient", clientControleur.addClient);

module.exports = router;