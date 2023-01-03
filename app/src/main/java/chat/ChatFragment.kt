package chat

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.userApiInterface
import com.example.frippy_v_fin.R
import com.example.frippy_v_fin.databinding.FragmentChatBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatFragment:Fragment(R.layout.fragment_chat) {
    lateinit var binding: FragmentChatBinding
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var rvchats:RecyclerView
    lateinit var chatAdapter : ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentChatBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireActivity().getSharedPreferences("FRIPPY",AppCompatActivity.MODE_PRIVATE)

        val connectedUser = sharedPreferences.getString("_id", null)
        rvchats=view.findViewById(R.id.rvChats)
        rvchats.setHasFixedSize(true)
        linearLayoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        rvchats.layoutManager=linearLayoutManager
        userApiInterface.create().userchats(connectedUser).enqueue(object : Callback<List<Chat>?>{
            override fun onResponse(call: Call<List<Chat>?>, response: Response<List<Chat>?>) {
                val responseBody=response.body()!!

                chatAdapter= ChatAdapter(this@ChatFragment.requireContext(),responseBody)
                chatAdapter.notifyDataSetChanged()
                rvchats.adapter =chatAdapter
                println(response.body()!!.toString())


            }

            override fun onFailure(call: Call<List<Chat>?>, t: Throwable) {
                println("-------------------")
                println(connectedUser.toString())
            }

        })

    }


}