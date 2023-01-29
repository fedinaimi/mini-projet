const stories = require("../models/stories");
const ObjectId = require("mongodb").ObjectId;
exports.addstories = (req, res) => {
 
    let newstories = new stories({ client:req.body.client ,image:req.file.filename});
  
    newstories.save((erro, newstories) => {
      if (erro) {
        return res.status(400).json({
          error: "unable to add brand",
        });
      }
      return res.json({
        message: "sucsess",
        newstories,
      });
    });
    console.log(newstories);
  };
  exports.allstories = async (req, res) => {
    try {
      const bou = await stories.find()
      .populate('client','firstName')
    
      res.json(bou)
  
    } catch (error) {
      res.status(500).json(error)
    }
  }
