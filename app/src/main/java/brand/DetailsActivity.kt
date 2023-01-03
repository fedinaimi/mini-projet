package brand

import adapters.ProduitListAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.boutiqueApiInterface
import api.userApiInterface
import chat.Chat
import com.example.frippy_v_fin.R
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import models.BoutiqueModelItem
import models.ProduitModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {
    lateinit var recyclerViewProduit: RecyclerView
    lateinit var produitListAdapter: ProduitListAdapter
    var produitList: MutableList<ProduitModel> =
        emptyList<ProduitModel>().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boutiquedet)
        val id = intent.getStringExtra("brand id")
        val name = intent.getStringExtra("brand name")
        val phone = intent.getStringExtra("brand phone")
        val adress = intent.getStringExtra("brand adress")
        val client = intent.getStringExtra("brand client")
        val sharedPreferences = getSharedPreferences("FRIPPY", MODE_PRIVATE)
        val brandName = findViewById<TextView>(R.id.boutiquenom)
        val brandPhone = findViewById<TextView>(R.id.telfP)
        val brandAdress = findViewById<TextView>(R.id.boutiqueadress)
        val addProductButton = findViewById<Button>(R.id.buttonProduit)

        val room = findViewById<Button>(R.id.buttonchat)


        room.setOnClickListener { createchat() }

        brandName.text = name
        brandPhone.text = phone
        brandAdress.text = adress
       // brandAdress.text = client
        val user_id=sharedPreferences.getString("_id", null)
        var boutique: BoutiqueModelItem
        recyclerViewProduit = findViewById(R.id.boutiqueproduitList)
        produitListAdapter = ProduitListAdapter(produitList)
        recyclerViewProduit.adapter = produitListAdapter
        recyclerViewProduit.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        val apiInterface = boutiqueApiInterface.create()
        apiInterface.getProducts(id!!).enqueue(object : Callback<BoutiqueModelItem> {
            override fun onResponse(
                call: Call<BoutiqueModelItem>,
                response: Response<BoutiqueModelItem>
            ) {

                    println(user_id)
                    println(client)
                    boutique = response.body()!!
                    boutique.produits?.let {
                        produitList.addAll(it)
                        produitListAdapter.notifyDataSetChanged()

                    }



            if (user_id!=client) {
                addProductButton.visibility = View.INVISIBLE
            }
            }

            override fun onFailure(call: Call<BoutiqueModelItem>, t: Throwable) {

                Toast.makeText(applicationContext, "${t.cause}", Toast.LENGTH_SHORT).show()
            }
        })

        addProductButton.setOnClickListener {
            val intent = Intent(applicationContext, Addproduit::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }


    }

    private fun deleteProduit() {

    }


    private fun createchat() {
        val apiInterface = userApiInterface.create()
        val sharedPreferences = getSharedPreferences("FRIPPY", MODE_PRIVATE)
        val client = intent.getStringExtra("brand client")
        val user_id=sharedPreferences.getString("_id", null)
        val paramObject=JSONObject()
        paramObject.put("senderId",user_id);
        paramObject.put("receiverId",client);
        val JsonParser=JsonParser();
        var gsonObject=JsonParser.parse(paramObject.toString())as JsonObject

        apiInterface.createChat(gsonObject).enqueue(object : Callback<Chat> {
            override fun onResponse(
                call: Call<Chat>,
                response: Response<Chat>
            ) {
               println(response.body()!!.toString())
            }

            override fun onFailure(call: Call<Chat>, t: Throwable) {

                Toast.makeText(applicationContext, "${t.cause}", Toast.LENGTH_SHORT).show()
            }
        })



   }
}