package com.example.e_commerce_app

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce_app.model.Product
import com.squareup.picasso.Picasso

class ProductsAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        val holder = ProductViewHolder(view)
        view.setOnClickListener {
            val intent = Intent(parent.context, ProductDetails::class.java)
            intent.putExtra("title", products[holder.adapterPosition].title)
            intent.putExtra("photo", products[holder.adapterPosition].photoUrl)
            intent.putExtra("price", products[holder.adapterPosition].price.toString())
            intent.putExtra("isOnSale", products[holder.adapterPosition].isOnSale)
            parent.context.startActivity(intent)
        }
        return holder
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.title.text = products[position].title
        holder.price.text = products[position].price.toString()
        Picasso.get().load(products[position].photoUrl).into(holder.image)
//        Glide.with(context).load(Uri.parse(products[position].photoUrl)).into(holder.image)
        if(product.isOnSale) {
            holder.isOnSale.visibility = View.VISIBLE
        } else {
            holder.isOnSale.visibility = View.GONE
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_photo)
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
        val isOnSale: ImageView = itemView.findViewById(R.id.isOnSale)
    }
}
