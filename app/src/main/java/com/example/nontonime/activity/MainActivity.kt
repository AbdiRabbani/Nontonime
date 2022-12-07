package com.example.nontonime.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.nontonime.R
import com.example.nontonime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var _appBarConfig : AppBarConfiguration? = null
    private val appBarConfiguration get() = _appBarConfig as AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.myToolbar)
        setSupportActionBar(toolbar)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val drawerLayout = binding.drawer
        val navView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_container)

        _appBarConfig = AppBarConfiguration(
            setOf(
                R.id.home,
                R.id.bookmark,
                R.id.setting
            ),drawerLayout
        )
        setupActionBarWithNavController(navController, drawerLayout)
        navView.setupWithNavController(navController)

        val btnSearch = findViewById<ImageButton>(R.id.btn_search_toolbar)

        btnSearch.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }



    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(
            R.id.nav_host_fragment_container
        )
        return super.onSupportNavigateUp() || navController.navigateUp(appBarConfiguration)
    }


}