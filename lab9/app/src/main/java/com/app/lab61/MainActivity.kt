package com.app.lab61

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var sensorList: List<Sensor>
    private val sensorTextViews = mutableMapOf<Int, TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout = findViewById<LinearLayout>(R.id.sensor_layout)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)

        sensorList.forEach { sensor ->
            val textView = TextView(this).apply {
                text = sensor.name
                textSize = 18f
                setPadding(8, 8, 8, 8)
            }
            layout.addView(textView)
            sensorTextViews[sensor.type] = textView
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Реакция на изменение точности датчика, если необходимо
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val sensorType = it.sensor.type
            val values = it.values.joinToString(", ") { value -> "%.2f".format(value) }
            sensorTextViews[sensorType]?.text = "${it.sensor.name}\n$values\n\n"
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        sensorList.forEach { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }
}