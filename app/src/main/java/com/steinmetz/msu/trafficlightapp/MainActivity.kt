package com.steinmetz.msu.trafficlightapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.view.isGone

class MainActivity : AppCompatActivity() {

    private var trafficLightRed = findViewById<View>(R.id.trafficLightRed)
    private var trafficLightYellow = findViewById<View>(R.id.trafficLightYellow)
    private var trafficLightGreen = findViewById<View>(R.id.trafficLightGreen)
    private lateinit var changeLightButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       changeLightButton = findViewById<Button>(R.id.changeLightButton)

        changeLightButton.setOnClickListener {
            if (!trafficLightRed.isGone) {
                trafficLightRed.isGone = true
                trafficLightGreen.isGone = false
                changeLightButton.setBackgroundColor(Color.parseColor("#228B22"))
            } else if (!trafficLightGreen.isGone) {
                trafficLightGreen.isGone = true
                trafficLightYellow.isGone = false
                changeLightButton.setBackgroundColor(Color.parseColor("#F1FC00"))
            } else {
                trafficLightYellow.isGone = true
                trafficLightRed.isGone = false
                changeLightButton.setBackgroundColor(Color.parseColor("#B70000"))
            }
        }

    }

    private fun updateLight() {



    }

}