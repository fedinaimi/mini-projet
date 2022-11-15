const express = require("express");
const { check } = require("express-validator");
const router = express.Router();
const adminControleur = require("../controllers/admins");

router.post("/addAdmin", adminControleur.addAdmin);
router.get("/getadmins", adminControleur.getAllAdmins);
router.get("/getadminbyid/:id", adminControleur.getAdminById);
router.get("/getadminbyemail/:email", adminControleur.getAdminByEmail);
router.post("/update/:id", adminControleur.update);
router.post("/deleted/:id", adminControleur.deleted);

module.exports = router;
