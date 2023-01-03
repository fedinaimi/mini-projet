package adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import brand.ProductDetailsActivity
import coil.load
import com.example.frippy_v_fin.R
import models.ProduitModel

class ProduitListAdapter(var produitList: MutableList<ProduitModel>) :
    RecyclerView.Adapter<ProduitListAdapter.ProduitListViewHolder>() {
    class ProduitListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var produitImage = itemView.findViewById<ImageView>(R.id.boutiqueDisplayItem)
        var produitName = itemView.findViewById<TextView>(R.id.produitname)
        var produitPrice = itemView.findViewById<TextView>(R.id.adresboutiqueDisplayItem)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProduitListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.frypiproduit_item, parent, false)
        return ProduitListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProduitListViewHolder, position: Int) {

        val name = produitList[position].produit
        val prix = produitList[position].prix
        val imageLink = produitList[position].image

        holder.produitImage.load("http://192.168.1.17:5000/images/$imageLink")
        holder.produitName.text = name
        holder.produitPrice.text = prix.toString()


        holder.itemView.setOnClickListener {
            val intent  = Intent(holder.itemView.context , ProductDetailsActivity::class.java)
            intent.putExtra("imageLink",imageLink)
            intent.putExtra("productName",name)
            intent.putExtra("productPrice",prix.toString())
            intent.putExtra("productDescription",produitList[position].description)
            holder.itemView.context.startActivity(intent)

        }

    }

    override fun getItemCount() = produitList.size






}