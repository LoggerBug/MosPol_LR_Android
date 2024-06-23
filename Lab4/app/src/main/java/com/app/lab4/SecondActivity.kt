package com.app.lab4

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {

    private lateinit var callbacksTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        callbacksTextView = findViewById(R.id.callbacksTextView)
        appendCallback("onCreate")
    }

    override fun onStart() {
        super.onStart()
        appendCallback("onStart")
    }

    override fun onResume() {
        super.onResume()
        appendCallback("onResume")
    }

    override fun onPause() {
        super.onPause()
        appendCallback("onPause")
    }

    override fun onStop() {
        super.onStop()
        appendCallback("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        appendCallback("onDestroy")
    }

    private fun appendCallback(callbackName: String) {
        val currentText = callbacksTextView.text.toString()
        val newText = "$currentText\n$callbackName called"
        callbacksTextView.text = newText
    }
}