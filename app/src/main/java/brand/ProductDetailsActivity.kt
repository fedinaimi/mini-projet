package brand

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import api.boutiqueApiInterface
import chat.Chat
import coil.load
import com.example.frippy_v_fin.R
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import models.panier
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val imageLink = intent.getStringExtra("imageLink")
        val name = intent.getStringExtra("productName")
        val prix = intent.getStringExtra("productPrice")
        val description = intent.getStringExtra("productDescription")

        val image = findViewById<ImageView>(R.id.ivDetails)
        val productName = findViewById<TextView>(R.id.tvDetailsProductName)
        val productPrice = findViewById<TextView>(R.id.tvDetailsProductPrice)
        val productDescription = findViewById<TextView>(R.id.tvDetailsProductDescription)

        productName.text = name
        productPrice.text = prix
        productDescription.text = description
        image.load("http://192.168.1.17:5000/images/$imageLink")

        val addtocart = findViewById<Button>(R.id.btnDetailsAddToCart)

        addtocart.setOnClickListener { addpanier() }


    }
    private fun addpanier() {
        val apiInterface = boutiqueApiInterface.create()
        val sharedPreferences = getSharedPreferences("FRIPPY", MODE_PRIVATE)
        val user_id=sharedPreferences.getString("_id", null)
        val pname = intent.getStringExtra("productName")
        val price = intent.getStringExtra("productPrice")
        val pimage = intent.getStringExtra("imageLink")
        val paramObject= JSONObject()
        paramObject.put("client",user_id);
        paramObject.put("nom",pname);
        paramObject.put("prix",price);
        paramObject.put("image",pimage);
        val JsonParser= JsonParser();
        var gsonObject=JsonParser.parse(paramObject.toString())as JsonObject
        apiInterface.addpanier(gsonObject).enqueue(object : Callback<panier> {
            override fun onResponse(
                call: Call<panier>,
                response: Response<panier>
            ) {
                println(response.body()!!.toString())
            }

            override fun onFailure(call: Call<panier>, t: Throwable) {

                Toast.makeText(applicationContext, "${t.cause}", Toast.LENGTH_SHORT).show()
            }
        })




    }
}