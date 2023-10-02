package com.steinmetz.msu.geoquiz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayout.TabGravity
import com.steinmetz.msu.geoquiz.databinding.ActivityMainBinding
import java.math.RoundingMode

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val quizViewModel: QuizViewModel by viewModels()


    private var correctVar = 0
    private var totalAnswered = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")
        updateQuestion()

        binding.trueButton.setOnClickListener { it: View ->
            checkAnswer(true)
            checkAnswered()


        }
        binding.falseButton.setOnClickListener { it: View ->
            checkAnswer(false)
            checkAnswered()

        }
        binding.nextButton.setOnClickListener { view: View ->
            quizViewModel.moveToNext()
            updateQuestion()
            checkAnswered()
        }

        binding.previousButton.setOnClickListener { view: View ->

            quizViewModel.moveToPrev()
            updateQuestion()
            checkAnswered()
        }

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
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)

    }


    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId: Int
        if (userAnswer == correctAnswer) {
            messageResId = R.string.correct_toast
            correctVar++
        } else {
            messageResId = R.string.incorrect_toast
        }
        quizViewModel.setAnswered()
        totalAnswered++
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        if (totalAnswered == quizViewModel.QuestionBankSize) {
            gradedQuiz()
        }
    }

    private fun checkAnswered() {
        if (quizViewModel.currentQuestionAnswered == true) {
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
            Toast.makeText(this, "0.0%", Toast.LENGTH_LONG).show()
        } else {
            score = "%.1f".format(correctVar.toDouble() / quizViewModel.QuestionBankSize * 100).toDouble()
            Toast.makeText(this, "$score%", Toast.LENGTH_LONG).show()
            correctVar = 0
        }


    }

}