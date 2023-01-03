package api

import android.media.Image
import brand.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiImage {

    @Multipart
    @POST("boutique/")
    fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("desc") desc: RequestBody

    ): Call<UploadResponse>

    companion object {
        operator fun invoke(): ApiImage{
            return Retrofit.Builder()
                .baseUrl("https://102.219.179.115/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiImage::class.java)
        }
    }
}