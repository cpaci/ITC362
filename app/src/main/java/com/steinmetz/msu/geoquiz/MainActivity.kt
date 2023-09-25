package com.steinmetz.msu.geoquiz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.tabs.TabLayout.TabGravity
import com.steinmetz.msu.geoquiz.databinding.ActivityMainBinding
import java.math.RoundingMode

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val questionBank = listOf(
        Question(R.string.question_australia, true, false),
        Question(R.string.question_oceans, true, false),
        Question(R.string.question_mideast, false, false),
        Question(R.string.question_africa, false, false),
        Question(R.string.question_americas, true, false),
        Question(R.string.question_asia, true, false)
    )
    private var currentIndex = 0
    private var correctVar = 0
    private var totalAnswered = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener { it: View ->
            checkAnswer(true)

        }
        binding.falseButton.setOnClickListener { it: View ->
            checkAnswer(false)

        }
        binding.nextButton.setOnClickListener { view: View ->
            if (currentIndex == questionBank.size - 1) {

                updateQuestion()
            } else {
                currentIndex = (currentIndex + 1) % questionBank.size
                updateQuestion()

            }
        }

        binding.questionTextView.setOnClickListener { view: View ->
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        binding.previousButton.setOnClickListener { view: View ->

            if (currentIndex >= 1 && currentIndex < questionBank.size) {
                currentIndex -= 1
                updateQuestion()
            } else if (currentIndex == 0) {
            }

        }
        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onStart() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
        checkAnswered()

    }


    private fun checkAnswer(userAnswer: Boolean) {
        questionBank[currentIndex].answered = true // Sets question answered var to true.
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId: Int
        if (userAnswer == correctAnswer) {
            messageResId = R.string.correct_toast
            correctVar++
        } else {
            messageResId = R.string.incorrect_toast
        }
        checkAnswered()
        totalAnswered++
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        if (totalAnswered == questionBank.size) {
            gradedQuiz()
        }
    }

    private fun checkAnswered() {
        if (questionBank[currentIndex].answered == true) {
            binding.trueButton.isEnabled = false
            binding.falseButton.isEnabled = false
        } else {
            binding.trueButton.isEnabled = true
            binding.falseButton.isEnabled = true
        }
    }

    private fun gradedQuiz() {
        var score: Double
        if (correctVar == 0) {
            Toast.makeText(this, "0.0%", Toast.LENGTH_SHORT).show()
        } else {
            score = "%.1f".format(correctVar.toDouble() / questionBank.size * 100).toDouble()
            Toast.makeText(this, "$score%", Toast.LENGTH_SHORT).show()
            correctVar = 0
        }


    }

}