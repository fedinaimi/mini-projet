package brand

import adapters.BoutiqueListAdapter
import adapters.PanierListAdapter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import api.boutiqueApiInterface
import com.example.frippy_v_fin.R
import models.BoutiqueModel
import models.BoutiqueModelItem
import models.PanierModel
import models.panier
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.abs

class CartActivity : AppCompatActivity() {
    lateinit var recyclerViewPanier: RecyclerView
    lateinit var panierListAdapter: PanierListAdapter
    var PanierList: MutableList<panier> = emptyList<panier>().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)








        val totalitem = findViewById<TextView>(R.id.tvLastSubTotalprice)

        recyclerViewPanier = findViewById(R.id.rvCartItems)
        panierListAdapter = PanierListAdapter(PanierList)
        recyclerViewPanier.adapter = panierListAdapter
        recyclerViewPanier.layoutManager= LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        val apiInterface = boutiqueApiInterface.create()
        val sharedPreferences = getSharedPreferences("FRIPPY", MODE_PRIVATE)
        val user_id=sharedPreferences.getString("_id", null)
        val price = intent.getStringExtra("productPrice")
         val totalprice = findViewById<TextView>(R.id.tvLastTotalPrice)

        val somme =panierListAdapter.somme()
        apiInterface.getPanier(user_id!!).enqueue(object : Callback<PanierModel> {
            override fun onResponse(call: Call<PanierModel>, response: Response<PanierModel>) {
                PanierList.addAll(response.body()!!)
                totalitem.text =  panierListAdapter.panierList.size.toString()
                totalprice.text =  PanierList.sumOf { panier: panier ->panier.prix!!.toInt() }.toString()
                panierListAdapter.notifyDataSetChanged()






            }

            override fun onFailure(call: Call<PanierModel>, t: Throwable) {
                Toast.makeText(applicationContext, "${t.cause}", Toast.LENGTH_SHORT).show()
            }

        })


    }
}



