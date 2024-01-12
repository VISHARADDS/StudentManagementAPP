package com.example.studentmanagement_it21822544.models

import com.example.studentmanagement_it21822544.data.StudentRepository
import com.example.studentmanagement_it21822544.models.validations.ValidationResults

class LoginForm( private var username:String, private var password:String, private val studentRepository: StudentRepository,private val studentId: String,) {



    //username
    fun validateUsername(): ValidationResults {
        return if (username.isEmpty()) {
            ValidationResults.Empty("Enter Username")
        } else if (!username.startsWith("IT")) {
            ValidationResults.Invalid("Username must start with IT")
        } else if (username.length != 10) {
            ValidationResults.Invalid("Username must contain 10 characters")
        } else if (!studentRepository.doesStudentExist(username)) {
            ValidationResults.Invalid("Student ID does not exist")
        } else {
            ValidationResults.Valid
        }
    }

    //password
    suspend fun validatePassword(): ValidationResults {
        return if (password.isEmpty()) {
            ValidationResults.Empty("Enter Password")
        } else {
            val studentEntity = studentRepository.getStudent(studentId)
            if (studentEntity != null && studentEntity.stdPass == password) {
                ValidationResults.Valid
            } else {
                ValidationResults.Invalid("Incorrect password")
            }
        }
    }
}