package com.steinmetz.msu.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import java.util.Objects

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val ARRAY_KEY = "ARRAY_KEY"
const val QUESTION_ONE = "QUESTION_ONE"
const val QUESTION_TWO = "QUESTION_TWO"
const val QUESTION_THREE = "QUESTION_THREE"
const val QUESTION_FOUR = "QUESTION_FOUR"
const val QUESTION_FIVE = "QUESTION_FIVE"
const val QUESTION_SIX = "QUESTION_SIX"





class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var questionBank = listOf(
        Question(R.string.question_australia, true, questionOne),
        Question(R.string.question_oceans, true, questionTwo),
        Question(R.string.question_mideast, false, questionThree),
        Question(R.string.question_africa, false, questionFour),
        Question(R.string.question_americas, true, questionFive),
        Question(R.string.question_asia, true, questionSix)
    )

    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    private var questionOne: Boolean
        get() = savedStateHandle.get(QUESTION_ONE) ?: false
        set(value) = savedStateHandle.set(QUESTION_ONE, value)

    private var questionTwo: Boolean
        get() = savedStateHandle.get(QUESTION_TWO) ?: false
        set(value) = savedStateHandle.set(QUESTION_TWO, value)

    private var questionThree: Boolean
        get() = savedStateHandle.get(QUESTION_THREE) ?: false
        set(value) = savedStateHandle.set(QUESTION_THREE, value)

    private var questionFour: Boolean
        get() = savedStateHandle.get(QUESTION_FOUR) ?: false
        set(value) = savedStateHandle.set(QUESTION_FOUR, value)

    private var questionFive: Boolean
        get() = savedStateHandle.get(QUESTION_FIVE) ?: false
        set(value) = savedStateHandle.set(QUESTION_FIVE, value)

    private var questionSix: Boolean
        get() = savedStateHandle.get(QUESTION_SIX) ?: false
        set(value) = savedStateHandle.set(QUESTION_SIX, value)


    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    val questionBankSize: Int
        get() = questionBank.size

    val currentQuestionAnswered: Boolean
        get() = questionBank[currentIndex].answered

    fun moveToNext() {
        if (currentIndex < questionBank.size - 1){
            currentIndex = (currentIndex + 1) % questionBankSize
        }

    }
    fun moveToPrev() {

        if(currentIndex >= 1) {
            currentIndex -= 1
        } else if (currentIndex == 0) {
            return
        } else
        {
            currentIndex = questionBankSize - 1
        }
    }

fun isAnswered() {
    if(currentIndex == 0) {
        questionBank[currentIndex].answered = true
        questionOne = true
    } else if(currentIndex == 1) {
        questionBank[currentIndex].answered = true
        questionTwo = true
    }else if(currentIndex == 2) {
        questionBank[currentIndex].answered = true
        questionThree = true
    }else if(currentIndex == 3) {
        questionBank[currentIndex].answered = true
        questionFour = true
    }else if(currentIndex == 4) {
        questionBank[currentIndex].answered = true
        questionFive = true
    }else if(currentIndex == 5) {
        questionBank[currentIndex].answered = true
        questionSix = true
    }
}


}