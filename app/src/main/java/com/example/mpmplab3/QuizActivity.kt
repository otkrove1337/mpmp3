package com.example.mpmplab3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    private lateinit var questionText: TextView
    private lateinit var answerGroup: RadioGroup
    private lateinit var nextButton: Button

    private val questions = listOf(
        "What is the capital of Ukraine?",
        "Which planet is known as the Red Planet?",
        "Who wrote 'Romeo and Juliet'?"
    )
    private val answers = listOf(
        listOf("Bilibob", "London", "Kyiv", "Moscow"),
        listOf("Venus", "Mars", "Jupiter", "Uranus"),
        listOf("Shakespeare", "Andrew Lupashku", "Poroshenko", "Orwell")
    )
    private val correctAnswers = listOf(2, 1, 0)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionText = findViewById(R.id.questionText)
        answerGroup = findViewById(R.id.answerGroup)
        nextButton = findViewById(R.id.nextButton)

        loadQuestion()

        nextButton.setOnClickListener {
            val selectedId = answerGroup.checkedRadioButtonId
            if (selectedId != -1) {
                val selectedAnswerIndex = answerGroup.indexOfChild(findViewById(selectedId))
                if (selectedAnswerIndex == correctAnswers[currentQuestionIndex]) {
                    score++
                }

                currentQuestionIndex++
                if (currentQuestionIndex < questions.size) {
                    loadQuestion()
                } else {
                    // Pass the score to ResultActivity
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("SCORE", score)
                    startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(this, "Please select an answer!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadQuestion() {
        questionText.text = questions[currentQuestionIndex]
        answerGroup.clearCheck()

        val answerOptions = answers[currentQuestionIndex]
        (answerGroup.getChildAt(0) as RadioButton).text = answerOptions[0]
        (answerGroup.getChildAt(1) as RadioButton).text = answerOptions[1]
        (answerGroup.getChildAt(2) as RadioButton).text = answerOptions[2]
        (answerGroup.getChildAt(3) as RadioButton).text = answerOptions[3]
    }
}