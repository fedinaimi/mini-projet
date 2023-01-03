package api

import chat.Chat
import chat.Message
import com.google.gson.JsonObject
import models.User
import models.UserModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface userApiInterface {
    @POST("api/signin")
    fun signin(@Body user: Map<String, String>): Call<UserModel>

    @POST("api/signup")
    fun register(@Body userSignup: Map<String, String>): Call<UserModel>

    @GET("api/profile/{id}")
    fun getIndex(@Path("id") id: String?):Call<User>

    //-------------------------

    @GET("api/messages/{chatId}")
    fun getMessages(@Path("chatId") userId: String?):Call<MutableList<Message>>


    @POST("api/messages/")
    fun addMessages(@Body body: JsonObject) :Call<Message>

    @GET("User/{id}")
    fun getIndexUser(@Path("id") id: String?) : Call<User>
    @GET("Chat/{id}")
    fun userchats(@Path("id") id: String?):Call<List<Chat>>
    abstract fun changePasswordReset(passRes: Any): Any
    @POST("Chat/")
    fun createChat(@Body body: JsonObject) : Call<Chat>


    //----------------------------



    companion object {

        var BASE_URL = "http://192.168.1.17:5000/"
        //var BASE_URL = "http://102.219.179.115/"
        fun create(): userApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(userApiInterface::class.java)
        }
    }
}