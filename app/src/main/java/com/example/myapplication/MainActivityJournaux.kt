package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class MainActivityJournaux : AppCompatActivity() {

     lateinit var toogle :ActionBarDrawerToggle

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_journaux)

        var drawer: DrawerLayout = findViewById(R.id.drawer)
        var toolbar :Toolbar = findViewById(R.id.nav_view)

        // centralisation de la bar de menu
        setSupportActionBar(toolbar)




   // recuperation des items du menu

        toogle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(toogle)
        toogle.syncState()






    }

    private fun setSupportActionBar(toolbar: Toolbar) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}