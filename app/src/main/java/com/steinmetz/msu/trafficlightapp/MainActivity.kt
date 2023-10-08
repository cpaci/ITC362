package com.steinmetz.msu.trafficlightapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.view.isGone

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val changeLightButton: Button = findViewById<Button>(R.id.changeLightButton)
        val trafficLightRed: View = findViewById<View>(R.id.trafficLightRed)
        val trafficLightYellow: View = findViewById<View>(R.id.trafficLightYellow)
        val trafficLightGreen: View = findViewById<View>(R.id.trafficLightGreen)
        val superContainer: View = findViewById<View>(R.id.superContainer)

         fun setRedLight() {
            trafficLightGreen.isGone = true
            trafficLightYellow.isGone = true
        }

        setRedLight()

         fun updateLight() {
            if (!trafficLightRed.isGone) {
                trafficLightRed.isGone = true
                trafficLightGreen.isGone = false
                superContainer.setBackgroundColor(Color.parseColor("#549C30"))
            } else if (!trafficLightGreen.isGone) {
                trafficLightGreen.isGone = true
                trafficLightYellow.isGone = false
                superContainer.setBackgroundColor(Color.parseColor("#FFBF00"))
            } else {
                trafficLightYellow.isGone = true
                trafficLightRed.isGone = false
                superContainer.setBackgroundColor(Color.parseColor("#B70000"))
            }


        }
        changeLightButton.setOnClickListener { updateLight() }

        }






}