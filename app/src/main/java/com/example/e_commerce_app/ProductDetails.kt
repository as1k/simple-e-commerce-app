package com.example.e_commerce_app

import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class ProductDetails : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var price: TextView
    private lateinit var image: ImageView
    private lateinit var btnAvailability: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        image = findViewById(R.id.details_image)
        title = findViewById(R.id.details_title)
        price = findViewById(R.id.details_price)

        title.text = intent.getStringExtra("title")
        price.text = intent.getStringExtra("price")
        Picasso.get().load(intent.getStringExtra("photo")).into(image)
        val isOnSale = intent.getBooleanExtra("isOnSale", false)

        btnAvailability = findViewById(R.id.details_btn_availability)
        btnAvailability.setOnClickListener {
           AlertDialog.Builder(this)
               .setMessage(
                   if(isOnSale) {
                       "Hey, ${title.text} is in stock"
                   } else {
                       "Sorry, ${title.text} is not in stock"
                   }
               )
               .setPositiveButton("OK") { p0, p1 ->
                   // do nothing
               }
               .create()
               .show()
        }
    }
}