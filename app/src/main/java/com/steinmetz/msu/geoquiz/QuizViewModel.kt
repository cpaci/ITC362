package com.steinmetz.msu.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

const val QUESTION_ONE = "QUESTION_ONE"
const val QUESTION_TWO = "QUESTION_TWO"
const val QUESTION_THREE = "QUESTION_THREE"
const val QUESTION_FOUR = "QUESTION_FOUR"
const val QUESTION_FIVE = "QUESTION_FIVE"
const val QUESTION_SIX = "QUESTION_SIX"

const val QUESTION_ONE_CHEATED = "QUESTION_ONE_CHEATED"
const val QUESTION_TWO_CHEATED = "QUESTION_TWO_CHEATED"
const val QUESTION_THREE_CHEATED = "QUESTION_THREE_CHEATED"
const val QUESTION_FOUR_CHEATED = "QUESTION_FOUR_CHEATED"
const val QUESTION_FIVE_CHEATED = "QUESTION_FIVE_CHEATED"
const val QUESTION_SIX_CHEATED = "QUESTION_SIX_CHEATED"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var questionBank = listOf(
        Question(R.string.question_australia, true, questionOne, questionOneCheated),
        Question(R.string.question_oceans, true, questionTwo, questionTwoCheated),
        Question(R.string.question_mideast, false, questionThree, questionThreeCheated),
        Question(R.string.question_africa, false, questionFour, questionFourCheated),
        Question(R.string.question_americas, true, questionFive, questionFiveCheated),
        Question(R.string.question_asia, true, questionSix, questionSixCheated)
    )

    var isCheater: Boolean
        get() = savedStateHandle[IS_CHEATER_KEY] ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    private var currentIndex: Int
        get() = savedStateHandle[CURRENT_INDEX_KEY] ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    private var questionOne: Boolean
        get() = savedStateHandle[QUESTION_ONE] ?: false
        set(value) = savedStateHandle.set(QUESTION_ONE, value)

    private var questionTwo: Boolean
        get() = savedStateHandle[QUESTION_TWO] ?: false
        set(value) = savedStateHandle.set(QUESTION_TWO, value)

    private var questionThree: Boolean
        get() = savedStateHandle[QUESTION_THREE] ?: false
        set(value) = savedStateHandle.set(QUESTION_THREE, value)

    private var questionFour: Boolean
        get() = savedStateHandle[QUESTION_FOUR] ?: false
        set(value) = savedStateHandle.set(QUESTION_FOUR, value)

    private var questionFive: Boolean
        get() = savedStateHandle[QUESTION_FIVE] ?: false
        set(value) = savedStateHandle.set(QUESTION_FIVE, value)

    private var questionSix: Boolean
        get() = savedStateHandle[QUESTION_SIX] ?: false
        set(value) = savedStateHandle.set(QUESTION_SIX, value)

    private var questionOneCheated: Boolean
        get() = savedStateHandle[QUESTION_ONE_CHEATED] ?: false
        set(value) = savedStateHandle.set(QUESTION_ONE_CHEATED, value)

    private var questionTwoCheated: Boolean
        get() = savedStateHandle[QUESTION_TWO_CHEATED] ?: false
        set(value) = savedStateHandle.set(QUESTION_TWO_CHEATED, value)

    private var questionThreeCheated: Boolean
        get() = savedStateHandle[QUESTION_THREE_CHEATED] ?: false
        set(value) = savedStateHandle.set(QUESTION_THREE_CHEATED, value)

    private var questionFourCheated: Boolean
        get() = savedStateHandle[QUESTION_FOUR_CHEATED] ?: false
        set(value) = savedStateHandle.set(QUESTION_FOUR_CHEATED, value)

    private var questionFiveCheated: Boolean
        get() = savedStateHandle[QUESTION_FIVE_CHEATED] ?: false
        set(value) = savedStateHandle.set(QUESTION_FIVE_CHEATED, value)

    private var questionSixCheated: Boolean
        get() = savedStateHandle[QUESTION_SIX_CHEATED] ?: false
        set(value) = savedStateHandle.set(QUESTION_SIX_CHEATED, value)


    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    val questionBankSize: Int
        get() = questionBank.size

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionAnswered: Boolean
        get() = questionBank[currentIndex].answered
    val currentQuestionCheated: Boolean
        get() = questionBank[currentIndex].isCheated


    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBankSize
    }

    fun moveToPrev() {
        currentIndex = if (currentIndex >= 1) {
            (currentIndex - 1) % questionBankSize
        } else {
            questionBankSize - 1
        }
    }

    fun isAnswered() {
        questionBank[currentIndex].answered = true
    }

    fun isCheated() {
        questionBank[currentIndex].isCheated = true
    }
}