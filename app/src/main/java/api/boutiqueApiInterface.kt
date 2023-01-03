package api

import com.google.gson.JsonObject
import models.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface boutiqueApiInterface {
    @GET("boutique/allboutique")
    fun allBoutiques(): Call<BoutiqueModel>

    @GET("boutique/getoneboutique/{id}")
    fun getProducts(@Path(value = "id") id: String): Call<BoutiqueModelItem>
    @POST("boutique/addboutique")
    fun addboutique(@Body addboutique: Map<String, String>): Call<BoutiqueModelItem>
    @POST ("/panier/addpanier")
    fun addpanier(@Body body: JsonObject):Call<panier>
    @GET("/panier/{client}")
    fun getPanier(@Path(value = "client") client: String): Call<PanierModel>



    @POST("boutique/updateboutique/{id}")

    @JvmSuppressWildcards
    @Multipart
    fun addProduit(
        @Part image: MultipartBody.Part,
        @Part("produit") produit: String,
        @Part("quantite") quantite: Int,
        @Part("prix") prix: Int,
        @Part("description") description: String,
        @Path(value = "id") id: String):Call<ProduitModel>

    companion object {
        var BASE_URL = "http://192.168.1.17:5000/"
        //var BASE_URL = "http://102.219.179.115/"
        fun create(): boutiqueApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(boutiqueApiInterface::class.java)

        }
    }
}