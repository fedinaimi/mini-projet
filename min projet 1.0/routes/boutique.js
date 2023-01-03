const express = require("express");
const multer = require("../middlewares/multer-config");

const { addboutique,allboutique,update,getOneBoutique,deleted} = require("../controllers/boutique");

const router = express.Router();

router.post("/addboutique",multer, addboutique);
router.post("/updateboutique/:id",multer,update)

router.get("/allboutique", allboutique);

router.get("/getoneboutique/:id",getOneBoutique)

module.exports = router;
