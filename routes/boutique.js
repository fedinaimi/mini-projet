const express = require("express");
const multer = require("../middlewares/multer-config");

const { addboutique,allboutique,update,getOneBoutique,deleteboutique} = require("../controllers/boutique");

const router = express.Router();

router.post("/addboutique", addboutique);
router.post("/updateboutique/:id",multer,update)

router.get("/allboutique", allboutique);

router.get("/getoneboutique/:id",getOneBoutique)
router.delete("/delete/:id",deleteboutique)



module.exports = router;
