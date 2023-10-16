package com.steinmetz.msu.geoquiz

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.*
import org.junit.Test

class QuizViewModelTest {
    private val savedStateHandle = SavedStateHandle()
    private val quizViewModel = QuizViewModel(savedStateHandle)

    @Test
    fun providesExpectedQuestionText() {
        assertEquals(R.string.question_australia, quizViewModel.currentQuestionText)
    }

    @Test
    fun wrapsAroundQuestionBank() {
        val savedStateHandle = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to 5))
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_asia, quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        assertEquals(R.string.question_australia, quizViewModel.currentQuestionText)
    }

    @Test
    fun testsQuestionAnswer() {
        if (quizViewModel.currentQuestionAnswer == true){
            assertTrue(quizViewModel.currentQuestionAnswer)
        }
        if (quizViewModel.currentQuestionAnswer == false) {
            assertFalse(quizViewModel.currentQuestionAnswer)
        }
    }

    @Test
    fun testsQuestionFalse() {

    }
}



