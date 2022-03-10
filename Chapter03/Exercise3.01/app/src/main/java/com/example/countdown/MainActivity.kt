package com.example.countdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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
            while (value > 0) {
                delay(100)
                countdown()
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
    }
}