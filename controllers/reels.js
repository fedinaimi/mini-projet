const reels = require("../models/reels");
const ObjectId = require("mongodb").ObjectId;
exports.addreels = (req, res) => {
    let newreels = new reels({ ...req.body ,video:req.file.filename});
  
    newreels.save((erro, newreels) => {
      if (erro) {
        return res.status(400).json({
          error: "unable to add brand",
        });
      }
      return res.json({
        message: "sucsess",
        newreels,
      });
    });
    console.log(newreels);
  };
  exports.allreels = async (req, res) => {
    try {
      const bou = await reels.find()
      res.json(bou)
  
    } catch (error) {
      res.status(500).json(error)
    }
  }
