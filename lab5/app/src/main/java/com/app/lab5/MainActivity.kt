package com.app.lab5

import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var gestureDetector: GestureDetector
    private lateinit var textViewOutput: TextView
    private var touchCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewOutput = findViewById(R.id.textViewOutput)

        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                logEventInfo("Обнаружено одиночное касание", e)
                return super.onSingleTapConfirmed(e)
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                logEventInfo("Обнаружено двойное касание", e)
                return super.onDoubleTap(e)
            }

            override fun onLongPress(e: MotionEvent) {
                logEventInfo("Обнаружено долгое нажатие", e)
                super.onLongPress(e)
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                logEventInfo("Обнаружена прокрутка", e2)
                return super.onScroll(e1, e2, distanceX, distanceY)
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                logEventInfo("Обнаружен флинг", e2)
                return super.onFling(e1, e2, velocityX, velocityY)
            }

            override fun onDown(e: MotionEvent): Boolean {
                return true
            }
        })

        findViewById<TextView>(R.id.yourTextView).setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)

            // Обработка количества касаний
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                    touchCount++
                    updateTextViewOutput()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_CANCEL -> {
                    updateTextViewOutput()
                }
            }

            true
        }
    }

    private fun logEventInfo(message: String, event: MotionEvent?) {
        event?.let {
            val coordinates = "Координаты: (${it.x}, ${it.y})"
            val output = "$message \n$coordinates\n" +
                    "Количество касаний: $touchCount\n" +
                    "Индекс последнего касания: ${touchCount - 1}"
            textViewOutput.text = output
        }
    }

    private fun updateTextViewOutput() {
        val output = "Количество касаний: $touchCount\n" +
                "Индекс последнего касания: ${touchCount - 1}"
        textViewOutput.text = output
    }
}