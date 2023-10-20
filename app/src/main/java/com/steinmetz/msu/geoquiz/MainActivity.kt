package com.steinmetz.msu.geoquiz

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.steinmetz.msu.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val quizViewModel: QuizViewModel by viewModels()

    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Handle the result
        if (result.resultCode == Activity.RESULT_OK) {
            quizViewModel.isCheater =
                result.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }


    private var correctVar = 0
    private var totalAnswered = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, quizViewModel.isCheated.toString())


        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")
        updateQuestion()

        if (quizViewModel.currentQuestionAnswered) {
            checkAnswered()
        }

        binding.trueButton.setOnClickListener {
            checkAnswer(true)
            checkAnswered()
        }
        binding.falseButton.setOnClickListener {
            checkAnswer(false)
            checkAnswered()


        }
        binding.nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
            checkAnswered()
        }

        binding.previousButton.setOnClickListener {
            quizViewModel.moveToPrev()
            updateQuestion()
            checkAnswered()
        }

        binding.cheatButton.setOnClickListener {
            // Start Activity
            val hasCheated = quizViewModel.isCheater
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val currentQuestionCheated = quizViewModel.isCheated
            val intent = CheatActivity.newIntent(
                this@MainActivity,
                answerIsTrue,
                hasCheated,
                currentQuestionCheated
            )
            cheatLauncher.launch(intent)
        }

    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId: Int
        if (quizViewModel.isCheater) {
            messageResId = R.string.judgement_toast
        } else if (userAnswer == correctAnswer) {
            messageResId = R.string.correct_toast
            correctVar++
        } else {
            messageResId = R.string.incorrect_toast
        }
        totalAnswered++
        quizViewModel.isAnswered()
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        if (totalAnswered == quizViewModel.questionBankSize) {
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
            score = "%.1f".format(correctVar.toDouble() / quizViewModel.questionBankSize * 100)
                .toDouble()
            Toast.makeText(this, "$score%", Toast.LENGTH_LONG).show()
        }
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
}