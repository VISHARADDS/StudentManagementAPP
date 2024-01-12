package com.example.studentmanagement_it21822544.models

import com.example.studentmanagement_it21822544.data.StudentRepository
import com.example.studentmanagement_it21822544.models.validations.ValidationResults

class ResultForm(
    private var stdId:String,
    private var spnSemester: String,
    private var module1:String,
    private var module2: String,
    private var module3:String,


) {
    fun validateStudentId(): ValidationResults {
        return if (stdId.isEmpty()){
            ValidationResults.Empty("Fill the Student ID")
        }else if(!stdId.startsWith("IT")){
            ValidationResults.Invalid("Student ID starts with IT")
        }else if (stdId.length!=10) {
            ValidationResults.Invalid("must contain 10 characters")
        }
        else{
            ValidationResults.Valid
        }
    }

    fun validateSemester():ValidationResults{
        return if(spnSemester.isEmpty()){
            ValidationResults.Empty("Select the Semester")
        }else {
            ValidationResults.Valid
        }

    }
    fun validateModule1():ValidationResults{
        return if(module1.isEmpty()){
            ValidationResults.Empty("Module 1 is Empty")
        }else {
            ValidationResults.Valid
        }

    }
    fun validateModule2():ValidationResults{
        return if(module2.isEmpty()){
            ValidationResults.Empty("Module 2 is Empty")
        }else {
            ValidationResults.Valid
        }

    }
    fun validateModule3():ValidationResults{
        return if(module3.isEmpty()){
            ValidationResults.Empty("Module 3 is Empty")
        }else {
            ValidationResults.Valid
        }

    }
}