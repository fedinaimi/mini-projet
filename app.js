const mongoose = require("mongoose");

const express = require("express");
const bodyParser = require("body-parser");
var cookieParser = require("cookie-parser");
require("dotenv").config();

const port = process.env.PORT ||5000 ;

//import route
const userRoutes = require("./routes/user");
const adminRoutes = require("./routes/admin");
const clientRoutes = require("./routes/client");
const categrorieRoutes = require("./routes/categorie")
const produitRoutes = require("./routes/produit")





const app = express();

mongoose
  .connect(
    "mongodb://127.0.0.1:27017/Frippy"
  )
  .then(() => {
    console.log("Database connected!");
    // Starting a server
    app.listen(port, process.env.ALWAYSDATA_HTTP_ID, () => {
      console.log(`App is running at ${port}`);
    });
  })
  .catch((err) => console.log(err));

// for cors origin config
app.use((req, res, next) => {
  res.setHeader("Access-Control-Allow-Origin", "*");
  res.setHeader(
    "Access-Control-Allow-Headers",
    "Origin, X-Requested-With, Content, Accept, Content-Type, Authorization"
  );
  res.setHeader(
    "Access-Control-Allow-Methods",
    "GET, POST, PUT, DELETE, PATCH, OPTIONS"
  );
  next();
});
//use parsing middelware
app.use(bodyParser.json());
app.use(cookieParser());

app.use("/api", userRoutes);
app.use("/admin", adminRoutes);
app.use("/client",clientRoutes);
app.use("/categorie",categrorieRoutes);
app.use("/produit",produitRoutes);

