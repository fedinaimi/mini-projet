package models

data class BoutiqueModelItem(
    val __v: Int?=null,
    val _id: String?=null,
    val adresse: String?=null,
    val numtelf: String?=null,
    val nom: String?=null,
    val brandemail :String?=null,
    val linkSocialmedia: String?=null,
    val produits: Array<ProduitModel>?=null,
    val client: String?=null,
    val photo:String?=null,
    val destination: String? = null,
    val encoding: String? = null,
    val fieldname: String? = null,
    val filename:String? = null,
    val mimetype: String? = null,
    val originalname:String? = null,
    val path: String? = null,
    val size: Int? = null
)