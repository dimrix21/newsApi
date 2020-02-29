package com.dimrix.news.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.dimrix.news.R


class MainActivity : AppCompatActivity() {


    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.host_activity)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        // Get the NavController for your NavHostFragment
        navController = findNavController(R.id.nav_host_fragment)
        setSupportActionBar(toolbar)


        // Set up the ActionBar to stay in sync with the NavController
        setupActionBarWithNavController(navController)


        toolbar.setNavigationOnClickListener {
            //  navController.navigateUp()
            navController.popBackStack()

        }

    }
}