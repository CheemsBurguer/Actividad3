package com.pougang.actividad3

import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var scaleFactor = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Toast.makeText(this, "Hola pou", Toast.LENGTH_SHORT).show()

        val tvHome = findViewById<TextView>(R.id.tvHome)

        val btnHome = findViewById<Button>(R.id.btn_home)

        btnHome.setOnClickListener {
            if(tvHome.text == getString(R.string.poucificacion)) {
                tvHome.text = getString(R.string.tv_home)
            } else {
                tvHome.text = getString(R.string.poucificacion)
            }
        }

        val imgHome = findViewById<ImageView>(R.id.imageView)

        val scaleGesture = ScaleGestureDetector(this,
            object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
                override fun onScale(detector: ScaleGestureDetector): Boolean {
                    scaleFactor *= detector.scaleFactor

                    scaleFactor = scaleFactor.coerceIn(0.5f, 5.0f)

                    imgHome.scaleX = scaleFactor
                    imgHome.scaleY = scaleFactor
                    return true
                }
            })

        imgHome.setOnTouchListener { _, event ->
            scaleGesture.onTouchEvent(event)
            true
        }
    }
}