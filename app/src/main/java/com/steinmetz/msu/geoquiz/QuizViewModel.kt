package com.steinmetz.msu.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val questionBank = listOf(
        Question(R.string.question_australia, true, false),
        Question(R.string.question_oceans, true, false),
        Question(R.string.question_mideast, false, false),
        Question(R.string.question_africa, false, false),
        Question(R.string.question_americas, true, false),
        Question(R.string.question_asia, true, false)
    )
    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)


    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionAnswered: Boolean
        get() = questionBank[currentIndex].answered

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val QuestionBankSize: Int
        get() = questionBank.size

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun setAnswered() {
        questionBank[currentIndex].answered = true
    }

    fun moveToPrev() {

        if(currentIndex >= 1) {
            currentIndex -= 1
        } else {
            currentIndex = questionBank.size - 1
        }

    }


}