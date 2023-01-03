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
import models.panier

class PanierListAdapter(var panierList: MutableList<panier>) :
    RecyclerView.Adapter<PanierListAdapter.PanierListViewHolder>() {
    class PanierListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ProductImage: ImageView = itemView.findViewById(R.id.ivCartProduct)
        var ProductName: TextView = itemView.findViewById(R.id.tvCartProductName)
        var ProductPrice:  TextView = itemView.findViewById(R.id.tvCartProductPrice)

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PanierListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cartproduct_item, parent, false)
        return PanierListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PanierListViewHolder, position: Int) {
        val name = panierList[position].nom
        val prix = panierList[position].prix
        val imageLink = panierList[position].image

        holder.ProductImage.load("http://192.168.1.17:5000/images/$imageLink")
        holder.ProductName.text = name
        holder.ProductPrice.text = prix


    }


    override fun getItemCount() = panierList.size
    fun somme(): Int = panierList.sumOf { panier: panier -> panier.prix!!.toInt() }

}