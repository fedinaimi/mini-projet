package chat
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import api.userApiInterface
import com.example.frippy_v_fin.R

import com.example.frippy_v_fin.*
import models.User


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatAdapter(val context: Context, val chatList:List<Chat>) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val userName: TextView


        init {
            userName=itemView.findViewById(R.id.username)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.chat_singel_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val sharedPreferences = context.getSharedPreferences("FRIPPY", AppCompatActivity.MODE_PRIVATE)
        val connectedUser = sharedPreferences.getString("_id", null)
        val other = if(chatList[position].members!![0]==connectedUser){
            chatList[position].members!![1]
        } else{
            chatList[position].members!![0]
        }

        val apiInterface = userApiInterface.create()
        apiInterface.getIndex(other).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                holder.userName.text=response.body()!!.firstName
                println(response.body()!!.toString())
                holder.itemView.setOnClickListener{
                    val intent = Intent(holder.itemView.context, ChatActivity::class.java)
                    intent.apply {

                        putExtra(CHAT_ID, chatList[position]._id)
                        putExtra(OTHER_USER_ID, response.body()!!._id)
                        putExtra(OTHER_USER_NAME, response.body()!!.firstName)





                    }
                    holder.itemView.context.startActivity(intent)
                }

            }

            override fun onFailure(call: Call<User>, t: Throwable) {

                println("---------------------------------------------")
            }
        })




    }

    override fun getItemCount(): Int {
        return chatList.size
    }




}
