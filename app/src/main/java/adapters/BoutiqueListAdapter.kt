package adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import brand.DetailsActivity
import coil.load
import com.example.frippy_v_fin.R
import menu1.MainActivity
import models.BoutiqueModelItem

class BoutiqueListAdapter(var boutiqueList: MutableList<BoutiqueModelItem>) :
    RecyclerView.Adapter<BoutiqueListAdapter.BoutiqueListViewHolder>() {
    class BoutiqueListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       // val boutiqueImage: ImageView = itemView.findViewById(R.id.ivShoeDisplayItem)
        var boutiqueName: TextView = itemView.findViewById(R.id.tvNameShoeDisplayItem)
        var boutiqueadress: TextView = itemView.findViewById(R.id.tvPriceShoeDisplayItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoutiqueListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.frypyidisplaymain_item, parent, false)
        return BoutiqueListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoutiqueListViewHolder, position: Int) {
        val name = boutiqueList[position].nom
        val adress = boutiqueList[position].adresse
        val imageLink = boutiqueList[position].path
       // holder.boutiqueImage.load("http://192.168.1.17:5000/images/$imageLink")
        holder.boutiqueName.text = name
        holder.boutiqueadress.text = adress

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailsActivity::class.java)
            intent.putExtra("brand id", boutiqueList[position]._id)
            intent.putExtra("brand name", name)
            intent.putExtra("brand adress", adress)
            intent.putExtra("brand phone", boutiqueList[position].numtelf)
            intent.putExtra("brand client",boutiqueList[position].client)


            holder.itemView.context.startActivity(intent)
        }
    }


    override fun getItemCount() = boutiqueList.size

}