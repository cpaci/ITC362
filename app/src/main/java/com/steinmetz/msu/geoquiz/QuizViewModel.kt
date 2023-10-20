package com.steinmetz.msu.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

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

const val IS_CHEATER_KEY = "IS_CHEATER_KEY"
const val CURRENT_QUESTION_IS_CHEATED = "CURRENT_QUESTION_IS_CHEATED"
const val CURRENT_QUESTION_ANSWER = "CURRENT_QUESTION_ANSWER"


class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var questionBank = listOf(
        Question(R.string.question_australia, true, questionOne, questionOneCheated),
        Question(R.string.question_oceans, true, questionTwo, questionTwoCheated),
        Question(R.string.question_mideast, false, questionThree, questionThreeCheated),
        Question(R.string.question_africa, false, questionFour, questionFourCheated),
        Question(R.string.question_americas, true, questionFive, questionFiveCheated),
        Question(R.string.question_asia, true, questionSix, questionSixCheated)
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


    private var questionOneCheated: Boolean
        get() = savedStateHandle.get(QUESTION_ONE_CHEATED) ?: false
        set(value) = savedStateHandle.set(QUESTION_ONE_CHEATED, value)

    private var questionTwoCheated: Boolean
        get() = savedStateHandle.get(QUESTION_TWO_CHEATED) ?: false
        set(value) = savedStateHandle.set(QUESTION_TWO_CHEATED, value)

    private var questionThreeCheated: Boolean
        get() = savedStateHandle.get(QUESTION_THREE_CHEATED) ?: false
        set(value) = savedStateHandle.set(QUESTION_THREE_CHEATED, value)

    private var questionFourCheated: Boolean
        get() = savedStateHandle.get(QUESTION_FOUR_CHEATED) ?: false
        set(value) = savedStateHandle.set(QUESTION_FOUR_CHEATED, value)

    private var questionFiveCheated: Boolean
        get() = savedStateHandle.get(QUESTION_FIVE_CHEATED) ?: false
        set(value) = savedStateHandle.set(QUESTION_FIVE_CHEATED, value)

    private var questionSixCheated: Boolean
        get() = savedStateHandle.get(QUESTION_SIX_CHEATED) ?: false
        set(value) = savedStateHandle.set(QUESTION_SIX_CHEATED, value)

    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: questionBank[currentIndex].isCheated
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    var isCheated: Boolean
        get() = savedStateHandle.get(CURRENT_QUESTION_IS_CHEATED)
            ?: questionBank[currentIndex].isCheated
        set(value) = savedStateHandle.set(CURRENT_QUESTION_IS_CHEATED, value)


    val currentQuestionAnswer: Boolean
        get() = savedStateHandle.get(CURRENT_QUESTION_ANSWER) ?: questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    val questionBankSize: Int
        get() = questionBank.size

    val currentQuestionAnswered: Boolean
        get() = questionBank[currentIndex].answered


    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBankSize
        isCheated = questionBank[currentIndex].isCheated

        if (!questionBank[currentIndex].isCheated) {
            isCheater = false
        }
    }

    fun moveToPrev() {

        if (currentIndex >= 1) {
            currentIndex = (currentIndex - 1) % questionBankSize
        } else {
            currentIndex = questionBankSize - 1
        }
        if (!questionBank[currentIndex].isCheated) {
            isCheater = false
        }
    }

    fun isAnswered() {
        when (currentIndex) {
            0 -> {
                questionBank[currentIndex].answered = true
                questionOne = true
            }

            1 -> {
                questionBank[currentIndex].answered = true
                questionTwo = true
            }

            2 -> {
                questionBank[currentIndex].answered = true
                questionThree = true
            }

            3 -> {
                questionBank[currentIndex].answered = true
                questionFour = true
            }

            4 -> {
                questionBank[currentIndex].answered = true
                questionFive = true
            }

            5 -> {
                questionBank[currentIndex].answered = true
                questionSix = true
            }
        }
    }

    fun setCheated() {
        when (currentIndex) {
            0 -> {
                questionOneCheated = true
                questionBank[currentIndex].isCheated = true
            }

            1 -> {
                questionTwoCheated = true
                questionBank[currentIndex].isCheated = true
            }

            2 -> {
                questionThreeCheated = true
                questionBank[currentIndex].isCheated = true
            }

            3 -> {
                questionFourCheated = true
                questionBank[currentIndex].isCheated = true
            }

            4 -> {
                questionFiveCheated = true
                questionBank[currentIndex].isCheated = true
            }

            5 -> {
                questionSixCheated = true
                questionBank[currentIndex].isCheated = true
            }

            else -> {
                return
            }
        }
        isCheater = true
    }

}