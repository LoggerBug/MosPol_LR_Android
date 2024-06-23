package com.app.lab2

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val shapeContainer = findViewById<LinearLayout>(R.id.main_layout)

        when (item.itemId) {
            R.id.red -> {
                shapeContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.holo_red_light))
                return true
            }
            R.id.green -> {
                shapeContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.holo_green_light))
                return true
            }
            R.id.blue -> {
                shapeContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.holo_blue_light))
                return true
            }
            R.id.exit -> {
                finish()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}

