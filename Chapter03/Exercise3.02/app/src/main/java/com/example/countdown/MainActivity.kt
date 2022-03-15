package com.example.countdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main)
    private lateinit var job: Job
    private lateinit var textView: TextView
    private var value = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        job = scope.launch {
            try {
                while (value > 0) {
                    delay(100)
                    countdown()
                }
            } catch (exception: Exception) {
                Snackbar.make(textView, exception.message.toString(), Snackbar.LENGTH_LONG).show()
            }
        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            job.cancel()
        }
    }

    private fun countdown() {
        value--
        textView.text = value.toString()
        if ((0..9).random() == 0) throw Exception("An error occurred")
    }
}