package chat


const val CHAT_ID = "CHAT_ID"
const val OTHER_USER_ID = "OTHER_USER"
const val OTHER_USER_NAME = "OTHER_USER_NAME"

data class Chat(
    var members:Array<String>?=null,
    var _id:String
)
