package com.app.lab1

import android.graphics.Typeface
import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.lab1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateSize()
        updateText()
        updateFont()
        updateColor()
    }

    fun updateColor(){
        binding.red.setOnClickListener {
            binding.text.setTextColor(ContextCompat.getColor(this, R.color.red))
        }

        binding.black.setOnClickListener {
            binding.text.setTextColor(ContextCompat.getColor(this, R.color.black))
        }

        binding.blue.setOnClickListener {
            binding.text.setTextColor(ContextCompat.getColor(this, R.color.blue))
        }

        binding.pink.setOnClickListener {
            binding.text.setTextColor(ContextCompat.getColor(this, R.color.pink))
        }

        binding.green.setOnClickListener {
            binding.text.setTextColor(ContextCompat.getColor(this, R.color.green))
        }

        binding.tifany.setOnClickListener {
            binding.text.setTextColor(ContextCompat.getColor(this, R.color.tifany))
        }

        binding.yellow.setOnClickListener {
            binding.text.setTextColor(ContextCompat.getColor(this, R.color.yellow))
        }
    }

    fun updateFont(){
        binding.sanSerif.setOnClickListener {
            binding.text.typeface = Typeface.SANS_SERIF
        }

        binding.serif.setOnClickListener {
            binding.text.typeface = Typeface.SERIF
        }

        binding.monospace.setOnClickListener {
            binding.text.typeface = Typeface.MONOSPACE
        }
    }

    fun updateText(){
        var isBold = false
        var isItalic = false

        binding.bold.setOnClickListener {
            if (!isBold){
                binding.text.setTypeface(null, Typeface.BOLD)
                isBold = true
            }else{
                binding.text.setTypeface(null, Typeface.NORMAL)
                isBold = false
            }
        }

        binding.italic.setOnClickListener {
            if (!isItalic){
                binding.text.setTypeface(null, Typeface.ITALIC)
                isBold = true
            }else{
                binding.text.setTypeface(null, Typeface.NORMAL)
                isBold = false
            }
        }

    }

    private fun updateSize(){
        var size = 15f

        binding.plus.setOnClickListener {
            size++
            binding.text.setTextSize(size)
            binding.textSize.text ="Text size : ${size.toInt()}"
        }

        binding.minus.setOnClickListener {
            size--
            binding.text.setTextSize(size)
            binding.textSize.text ="Text size : ${size.toInt()}"
        }
    }

}