package com.example.e_commerce_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce_app.model.Product
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainFragment : Fragment() {
    private lateinit var productsRecView: RecyclerView
    private lateinit var categoriesRecView: RecyclerView
    private lateinit var products: List<Product>
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        productsRecView = rootView.findViewById(R.id.products_recycler_view)
        categoriesRecView = rootView.findViewById(R.id.categoriesRecyclerView)
        progressBar = rootView.findViewById(R.id.progressBar)


        doAsync {
            val json = URL("https://finepointmobile.com/data/products.json").readText()

            uiThread {
                products = Gson().fromJson(json, Array<Product>::class.java).toList()
                productsRecView.apply {
                    layoutManager = GridLayoutManager(activity, 2)
                    adapter = ProductsAdapter(products)
                    progressBar.visibility = View.GONE
                }}
        }

        val categories = listOf("Jeans", "Socks", "Skirts", "Suits", "Dresses", "T-shirts", "Hats", "Trousers")

        categoriesRecView.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = CategoriesAdapter(categories)
        }

        return rootView
    }
}