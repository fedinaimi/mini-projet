package models

data class ProduitModel(
    var produit: String? = null,
    var quanitite: Int? = null,
    var prix: Int? = null,
    var description: String? = null,
    val image:String?=null,
    val message: String? = null,
    val destination: String? = null,
    val encoding: String? = null,
    val fieldname: String? = null,
    val filename:String? = null,
    val mimetype: String? = null,
    val originalname:String? = null,
    val path: String? = null,
    val size: Int? = null
)
