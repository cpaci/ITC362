package com.steinmetz.msu.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.steinmetz.msu.geoquiz.databinding.ActivityCheatBinding

const val EXTRA_ANSWER_SHOWN = "com.steinmetz.android.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.steinmetz.android.geoquiz.answer_is_true"
private const val EXTRA_IS_ALREADY_CHEATER = "EXTRA_IS_ALREADY_CHEATER"

class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding
    private val CheatViewModel: CheatViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        val isAlreadyCheater = intent.getBooleanExtra(EXTRA_IS_ALREADY_CHEATER, false)
        CheatViewModel.setAnswerIsTrue(answerIsTrue)
        setAnswerShownResult(CheatViewModel.isAnswerShown())
        val answerText = when {
            answerIsTrue -> R.string.true_button
            else -> R.string.false_button
        }
        if(isAlreadyCheater) {
            binding.answerTextView.setText(answerText)
        }
        if(CheatViewModel.isAnswerShown()) {
            binding.answerTextView.setText(answerText)
        }

        binding.showAnswerButton.setOnClickListener {
            binding.answerTextView.setText(answerText)
            CheatViewModel.setAnswerShown(true)
            setAnswerShownResult(CheatViewModel.isAnswerShown())
        }
    }


    fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean, isAlreadyCheater: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
                putExtra(EXTRA_IS_ALREADY_CHEATER, isAlreadyCheater)
            }
        }
    }
}