package chat

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("chatId")
    var chatId:String,
    @SerializedName("senderId")
    var senderId:String,
    @SerializedName("text")
    var text:String
)
