package com.example.studentmanagement_it21822544.models.validations

sealed class ValidationResults{
    data class Empty(val errorMessage: String): ValidationResults()
    data class Invalid(val errorMessage: String): ValidationResults()
    object Valid: ValidationResults()
}
