package com.example.studentmanagement_it21822544.models

import com.example.studentmanagement_it21822544.models.validations.ValidationResults

class StudentForm(
    private var studentId:String,
    private var studentName: String,
    private var spinnerProgram:String,
    private var spinnerFaculty: String,
    private var nic:String,
    private var stdPass:String,
    private var email:String,
    private var mobile:String

) {
    fun validateStudentId():ValidationResults{
        return if (studentId.isEmpty()){
            ValidationResults.Empty("Fill the Student ID")
        }else if(!studentId.startsWith("IT")){
            ValidationResults.Invalid("Student ID starts with IT")
        }else if (studentId.length!=10){
            ValidationResults.Invalid("must contain 10 characters")
        }else{
            ValidationResults.Valid
        }
    }

    fun validateStudentName():ValidationResults{
        return if (studentName.isEmpty()){
            ValidationResults.Empty("Fill the Student Name")
        }else{
            ValidationResults.Valid
        }
    }

    fun validateProgram():ValidationResults{
        return if (spinnerProgram.isEmpty()){
            ValidationResults.Empty("Select the program")
        }else {
            ValidationResults.Valid
        }
    }
    fun validateFaculty():ValidationResults{
        return if (spinnerFaculty.isEmpty()){
            ValidationResults.Empty("Select the Faculty")
        }else {
            ValidationResults.Valid
        }
    }
    fun validateNic():ValidationResults{
        val nicPattern = Regex("^[0-9]{9}(v)?|^[0-9]{12}$")
        return if (nic.isEmpty()) {
            ValidationResults.Empty("Enter NIC")
        }else if(!nicPattern.matches(nic)){
            ValidationResults.Invalid("Invalid NIC Format")
        }else {
            ValidationResults.Valid
        }
    }

    fun validateStdPassword():ValidationResults{
        return  if (stdPass.isEmpty()){
            ValidationResults.Empty("Enter Student Password")
        }else if (stdPass!=nic){
            ValidationResults.Invalid("Incorrect Password")
        }else {
            ValidationResults.Valid
        }
    }
    fun validateEmail(): ValidationResults {
        return if (email.isEmpty()) {
            ValidationResults.Empty("Enter Sliit Email")
        } else {
            val studentIdNumbers = studentId.replace("IT", "")
            val emailRegex = Regex("it$studentIdNumbers@my.sliit\\.lk")
            if (!email.matches(emailRegex)) {
                ValidationResults.Invalid("Invalid email format")
            } else {
                ValidationResults.Valid
            }
        }
    }

    fun validateMobile():ValidationResults{
        return if (mobile.isEmpty()){
            ValidationResults.Empty("Enter mobile number")
        }else if(mobile.length!=10){
            ValidationResults.Invalid("Invalid mobile number format")
        }else {
            ValidationResults.Valid
        }
    }

}