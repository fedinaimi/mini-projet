package Home

import adapters.BoutiqueListAdapter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.abs

class HomeGeneral : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: ImageAdapter

    //boutiqueList
    lateinit var recyclerViewBoutique: RecyclerView
    lateinit var recyclerViewboutiqueAdapter: BoutiqueListAdapter
    var boutiqueList: MutableList<BoutiqueModelItem> =
        emptyList<BoutiqueModelItem>().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_general)
        recyclerViewBoutique = findViewById(R.id.rvMainProductsList)
        recyclerViewboutiqueAdapter = BoutiqueListAdapter(boutiqueList)
        recyclerViewBoutique.adapter = recyclerViewboutiqueAdapter
        recyclerViewBoutique.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        val apiInterface = boutiqueApiInterface.create()
        apiInterface.allBoutiques().enqueue(object : Callback<BoutiqueModel> {
            override fun onResponse(call: Call<BoutiqueModel>, response: Response<BoutiqueModel>) {
                boutiqueList.addAll(response.body()!!)
                recyclerViewboutiqueAdapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<BoutiqueModel>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        init()
        setUptransformer()
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2000)
            }
        })
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable, 2000)
    }


    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }


    private fun setUptransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r + 0.14f
        }

        viewPager2.setPageTransformer(transformer)
    }

    private fun init() {
        viewPager2 = findViewById(R.id.viewPager2)
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()
        imageList.add(R.drawable.b11)
        imageList.add(R.drawable.b22)
        imageList.add(R.drawable.b33)


        adapter = ImageAdapter(imageList, viewPager2)
        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER


    }

}