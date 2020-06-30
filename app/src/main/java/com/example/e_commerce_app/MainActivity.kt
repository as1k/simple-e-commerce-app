package com.example.e_commerce_app

import android.os.Bundle
import android.util.Log.d
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout,
                MainFragment()
            )
            .commit()

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.action_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, MainFragment())
                        .commit()
                    d("tag", "menuItem 1 was pressed")
                }
                R.id.action_jeans -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, JeansFragment())
                        .commit()
                    d("tag", "menuItem 2 was pressed")
                }
                R.id.action_settings3 -> d("tag", "menuItem 3 was pressed")
                R.id.action_settings4 -> d("tag", "menuItem 4 was pressed")
                R.id.action_settings5 -> d("tag", "menuItem 5 was pressed")
            }
            it.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        drawerLayout.openDrawer(GravityCompat.START)
        return true
    }
}