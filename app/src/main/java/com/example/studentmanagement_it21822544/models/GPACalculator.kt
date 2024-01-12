package com.example.studentmanagement_it21822544.models

import kotlin.math.pow
import kotlin.math.roundToInt

class GPACalculator {
    companion object {
        private val gradingScale = mapOf(
            "A+" to 4.3,
            "A" to 4.0,
            "A-" to 3.7,
            "B+" to 3.3,
            "B" to 3.0,
            "B-" to 2.7,
            "C+" to 2.3,
            "C" to 2.0,
            "C-" to 1.7,
            "D" to 1.0,
            "E" to 0.0
        )

        fun calculateSemesterGPA(module1Grade: String, module2Grade: String, module3Grade: String): Double {
            if (gradingScale.containsKey(module1Grade) &&
                gradingScale.containsKey(module2Grade) &&
                gradingScale.containsKey(module3Grade)
            ) {
                val totalGradePoints = gradingScale[module1Grade]!! + gradingScale[module2Grade]!! + gradingScale[module3Grade]!!
                val gpa = totalGradePoints / 3.0


                return gpa.roundTo(2)
            } else {
                return -1.0
            }
        }

        private fun Double.roundTo(decimalPlaces: Int): Double {
            val factor = 10.0.pow(decimalPlaces.toDouble())
            return (this * factor).roundToInt() / factor
        }
    }
}