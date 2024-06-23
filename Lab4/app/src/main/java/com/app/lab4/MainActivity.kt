package com.app.lab4

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var callbacksTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callbacksTextView = findViewById(R.id.callbacksTextView)
        appendCallback("onCreate")

        // Обработчик кнопки для совершения звонка
        findViewById<Button>(R.id.makeCallButton).setOnClickListener {
            makePhoneCall()
        }

        // Обработчик кнопки для чтения контактов
        findViewById<Button>(R.id.readContactsButton).setOnClickListener {
            readContacts()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CALL_PHONE_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makePhoneCall()
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
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

    // Функция для совершения звонка
    private fun makePhoneCall() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                CALL_PHONE_REQUEST_CODE
            )
        } else {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:+79647824030") // Замените на нужный номер телефона
            startActivity(callIntent)
        }
    }

    // Функция для чтения контактов
    private fun readContacts() {
        val cursor: Cursor? = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null
        )
        cursor?.let {
            while (it.moveToNext()) {
                val contactName =
                    it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                appendCallback("Contact: $contactName")
            }
            it.close()
        }
    }

    companion object {
        private const val CALL_PHONE_REQUEST_CODE = 101
    }
}
