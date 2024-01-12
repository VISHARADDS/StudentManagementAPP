package com.example.studentmanagement_it21822544

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.studentmanagement_it21822544.data.RoomDb
import com.example.studentmanagement_it21822544.data.StudentEntity
import com.example.studentmanagement_it21822544.data.StudentRepository
import com.example.studentmanagement_it21822544.models.StudentForm
import com.example.studentmanagement_it21822544.models.validations.ValidationResults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddStudentActivity : AppCompatActivity() {
    lateinit var viewModel: StudentViewModel
    lateinit var registeredAdapter: RegisteredAdapter
    lateinit var studentId:EditText
    lateinit var studentName:EditText
    lateinit var spinnerProgram:Spinner
    lateinit var spinnerFaculty:Spinner
    lateinit var nic:EditText
    lateinit var stdPass:EditText
    lateinit var email:EditText
    lateinit var mobile:EditText

    private var count=0

    private val repository: StudentRepository by lazy {
        StudentRepository(RoomDb.getAppDatabase(this))
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)


        val repository = StudentRepository(RoomDb.getAppDatabase(this))

        viewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        studentId=findViewById(R.id.newStudentId)
        studentName=findViewById(R.id.newName)
        spinnerProgram=findViewById(R.id.newProgram)
        spinnerFaculty=findViewById(R.id.newFaculty)
        nic=findViewById(R.id.newNic)
        stdPass=findViewById(R.id.newPass)
        email=findViewById(R.id.newEmail)
        mobile=findViewById(R.id.newMobile)


        val back:Button=findViewById(R.id.back)
        back.setOnClickListener{
            val intent=Intent(this,StaffActivity::class.java)
            startActivity(intent)
        }



    }

    fun displayAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok") { dialog, which ->

        }
        val dialog = builder.create()
        dialog.show()
    }

    fun formAdd(view: View){
        val studentForm=StudentForm(
            studentId.text.toString(),
            studentName.text.toString(),
            spinnerProgram.selectedItem.toString(),
            spinnerFaculty.selectedItem.toString(),
            nic.text.toString(),
            stdPass.text.toString(),
            email.text.toString(),
            mobile.text.toString()


        )

        val studentIdValidate=studentForm.validateStudentId()
        val nameValidate=studentForm.validateStudentName()
        val spinnerProgramValidate=studentForm.validateProgram()
        val spinnerFacultyValidate=studentForm.validateFaculty()
        val nicValidate=studentForm.validateNic()
        val stdPassValidate=studentForm.validateStdPassword()
        val emailValidate=studentForm.validateEmail()
        val mobileValidate=studentForm.validateMobile()




        when(studentIdValidate){
            is ValidationResults.Valid->{
                count++
            }
            is ValidationResults.Invalid->{
                studentId.error=studentIdValidate.errorMessage
            }
            is ValidationResults.Empty->{
                studentId.error=studentIdValidate.errorMessage
            }

        }
        when(nameValidate){
            is ValidationResults.Valid->{
                count++
            }
            is ValidationResults.Invalid->{
                studentName.error=nameValidate.errorMessage
            }
            is ValidationResults.Empty->{
                studentName.error=nameValidate.errorMessage
            }

        }

        when (spinnerProgramValidate){
            is ValidationResults.Valid->{
                count++
            }
            is ValidationResults.Invalid->{

            }
            is ValidationResults.Empty->{
                val tv:TextView = spinnerProgram.selectedView as TextView
                tv.error =""
                tv.text = spinnerProgramValidate.errorMessage
            }
        }

        when (spinnerFacultyValidate){
            is ValidationResults.Valid->{
                count++
            }
            is ValidationResults.Invalid->{

            }
            is ValidationResults.Empty->{
                val tv:TextView = spinnerFaculty.selectedView as TextView
                tv.error =""
                tv.text = spinnerFacultyValidate.errorMessage
            }
        }

        when(nicValidate){
            is ValidationResults.Valid->{
                count++
            }
            is ValidationResults.Invalid->{
                nic.error=nicValidate.errorMessage
            }
            is ValidationResults.Empty->{
                nic.error=nicValidate.errorMessage
            }
        }

        when(stdPassValidate){
            is ValidationResults.Valid->{
                count++
            }
            is ValidationResults.Invalid->{
                stdPass.error=stdPassValidate.errorMessage
            }
            is ValidationResults.Empty->{
                stdPass.error=stdPassValidate.errorMessage
            }

        }



        when(emailValidate){
            is ValidationResults.Valid->{
                count++
            }
            is ValidationResults.Invalid->{
                email.error=emailValidate.errorMessage
            }
            is ValidationResults.Empty->{
                email.error=emailValidate.errorMessage

            }
        }

        when(mobileValidate){
            is ValidationResults.Valid->{
                count++
            }
            is ValidationResults.Invalid->{
                mobile.error=mobileValidate.errorMessage
            }
            is ValidationResults.Empty->{
                mobile.error=mobileValidate.errorMessage
            }
        }



        if (count==8){
            displayAlert("Success","Student Successfully Registered")
        }

        if (studentIdValidate is ValidationResults.Valid &&
            nameValidate is ValidationResults.Valid &&
            spinnerProgramValidate is ValidationResults.Valid &&
            spinnerFacultyValidate is ValidationResults.Valid &&
            nicValidate is ValidationResults.Valid &&
            stdPassValidate is ValidationResults.Valid &&
            emailValidate is ValidationResults.Valid &&
            mobileValidate is ValidationResults.Valid
     ){
            val studentEntity = StudentEntity(
                studentId.text.toString(),
                studentName.text.toString(),
                spinnerProgram.selectedItem.toString(),
                spinnerFaculty.selectedItem.toString(),
                nic.text.toString(),
                stdPass.text.toString(),
                email.text.toString(),
                mobile.text.toString()
            )

            CoroutineScope(Dispatchers.IO).launch{
                repository.insertStudent(studentEntity)
            }
            displayAlert("Success", "Student Successfully Registered")

            studentId.text.clear()
            studentName.text.clear()
            spinnerProgram.setSelection(0)
            spinnerFaculty.setSelection(0)
            nic.text.clear()
            stdPass.text.clear()
            email.text.clear()
            mobile.text.clear()

        }else{
            displayAlert("Error", "Student Registration Unsuccessful")
        }

    }


}