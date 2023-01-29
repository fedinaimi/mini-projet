const express = require("express");
const multer = require("../middlewares/multer-config");

const { addboutique,allboutique,myboutique,update,getOneBoutique,mybusiness,deleteProduit,allproduct} = require("../controllers/boutique");

const router = express.Router();

router.post("/addboutique", addboutique);
router.post("/updateboutique/:id",multer.single('image'),update)

router.get("/allboutique", allboutique);
router.get("/myboutique/:client", myboutique);

router.get("/getoneboutique/:id",getOneBoutique)
router.get("/mybusiness/:client",mybusiness)
router.patch("/deleteproduit/:id/:idProduit",deleteProduit)
router.get("/allproduct",allproduct)

module.exports = router;
