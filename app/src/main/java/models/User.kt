package models

import com.google.gson.annotations.SerializedName

data class User(
    val __v: Int,
    @SerializedName("_id")
    val _id: String,
    val created_at: String,
    val email: String,
    val firstName: String,
    val hash: String,
    val lastName: String,
    val salt: String,
    val verified: Boolean
)