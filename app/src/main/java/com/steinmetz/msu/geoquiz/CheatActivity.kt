package com.steinmetz.msu.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.steinmetz.msu.geoquiz.databinding.ActivityCheatBinding

private const val TAG = "CheatActivity"

const val EXTRA_ANSWER_SHOWN = "com.steinmetz.android.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.steinmetz.android.geoquiz.answer_is_true"
private const val EXTRA_HAS_CHEATED = "com.steinmetz.android.geoquiz.has_cheated"
private const val EXTRA_CURRENT_QUESTION_CHEATED =
    "com.steinmetz.android.geoquiz.current_question_cheated"

class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding

    private var answerIsTrue = false
    private var hasCheated = false
    private var currentQuestionCheated = false


    private val quizViewModel: QuizViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hasCheated = intent.getBooleanExtra(EXTRA_HAS_CHEATED, quizViewModel.isCheater)
        currentQuestionCheated =
            intent.getBooleanExtra(EXTRA_CURRENT_QUESTION_CHEATED, quizViewModel.isCheated)
        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        Log.d(TAG, quizViewModel.isCheated.toString())
        Log.d(TAG, "THIS IS CHEAT ACTIVITY INIT")
        var answerText = when {
            answerIsTrue -> R.string.true_button
            else -> R.string.false_button
        }

        if (hasCheated) {
            binding.answerTextView.setText(answerText)
            setAnswerShownResult(true)
        }
        if (quizViewModel.isCheater) {
            binding.answerTextView.setText(answerText)
            setAnswerShownResult(true)
        }


        Log.d(TAG, "Is Cheated set")
        Log.d(TAG, quizViewModel.isCheated.toString())

        binding.showAnswerButton.setOnClickListener {
            quizViewModel.setCheated()
            Log.d(TAG, quizViewModel.isCheated.toString())
            Log.d(TAG, "Show Answer pressed and isCheated set to true")
            binding.answerTextView.setText(answerText)
            setAnswerShownResult(true)
            quizViewModel.isCheater = true
        }
    }


    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(
            packageContext: Context,
            answerIsTrue: Boolean,
            hasCheated: Boolean,
            currentQuestionCheated: Boolean
        ): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
                putExtra(EXTRA_HAS_CHEATED, hasCheated)
                putExtra(EXTRA_CURRENT_QUESTION_CHEATED, currentQuestionCheated)
            }
        }
    }
}