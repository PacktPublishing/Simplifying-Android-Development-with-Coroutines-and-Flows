package com.example.countdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main)
    private var job: Job? = null
    private lateinit var textView: TextView
    private var count = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        job = scope.launch {
            try {
                while (count > 0) {
                    delay(100)
                    countdown()
                }
            } catch (exception: Exception) {
                Snackbar.make(textView, exception.message.toString(), Snackbar.LENGTH_LONG).show()
            }
        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            job?.cancel()
        }
    }

    private fun countdown() {
        count--
        textView.text = count.toString()
        if ((0..9).random() == 0) throw Exception("An error occurred")
    }
}