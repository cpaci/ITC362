package com.steinmetz.msu.geoquiz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.steinmetz.msu.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener { it: View ->
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener { it: View ->
            checkAnswer(false)
        }
        binding.nextButton.setOnClickListener { view: View ->
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        binding.questionTextView.setOnClickListener { view: View ->
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        binding.previousButton.setOnClickListener { view: View ->

            if (currentIndex >= 1 && currentIndex < questionBank.size) {
                currentIndex = currentIndex - 1
                updateQuestion()
            } else if (currentIndex == 0) {
                currentIndex = questionBank.size -1
                updateQuestion()
            }

        }
        updateQuestion()
    }
    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)


    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

}