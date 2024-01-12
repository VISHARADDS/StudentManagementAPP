package com.example.studentmanagement_it21822544

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
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

class UpdateStudentActivity : AppCompatActivity() {
    lateinit var viewModel: StudentViewModel
    private val repository: StudentRepository by lazy {
        StudentRepository(RoomDb.getAppDatabase(this))
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_student)


        val repository = StudentRepository(RoomDb.getAppDatabase(this))

        viewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        val studentId = intent.getStringExtra("studentId")
        val studentName = intent.getStringExtra("studentName")
        val spinnerProgram = intent.getStringExtra("spinnerProgram")
        val spinnerFaculty = intent.getStringExtra("spinnerProgram")
        val nic = intent.getStringExtra("nic")
        val stdPass = intent.getStringExtra("stdPass")
        val email = intent.getStringExtra("email")
        val mobile = intent.getStringExtra("mobile")


        val newStudentId = findViewById<EditText>(R.id.newStudentId)
        val newName = findViewById<EditText>(R.id.newName)
        val newProgram = findViewById<Spinner>(R.id.newProgram)
        val newFaculty = findViewById<Spinner>(R.id.newFaculty)
        val newNic = findViewById<EditText>(R.id.newNic)
        val newPass = findViewById<EditText>(R.id.newPass)
        val newEmail = findViewById<EditText>(R.id.newEmail)
        val newMobile = findViewById<EditText>(R.id.newMobile)

        val back5:Button=findViewById(R.id.back5)
        back5.setOnClickListener{
            val intent= Intent(this,RegisteredStudentsActivity::class.java)
            startActivity(intent)
        }


        newStudentId.setText(studentId)
        newName.setText(studentName)
        newNic.setText(nic)
        newPass.setText(stdPass)
        newEmail.setText(email)
        newMobile.setText(mobile)
        val programArray = resources.getStringArray(R.array.spinnerProgram)
        val facultyArray = resources.getStringArray(R.array.spinnerFaculty)

        val programAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, programArray)
        val facultyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, facultyArray)

        newProgram.adapter = programAdapter
        newFaculty.adapter = facultyAdapter


        val programPosition = programAdapter.getPosition(spinnerProgram)
        val facultyPosition = facultyAdapter.getPosition(spinnerFaculty)

        newProgram.setSelection(programPosition)
        newFaculty.setSelection(facultyPosition)

        val saveChanges = findViewById<Button>(R.id.saveChanges)

        fun displayAlert(title: String, message: String) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton("Ok") { dialog, which ->

            }
            val dialog = builder.create()
            dialog.show()
        }

        saveChanges.setOnClickListener {
            val updatedStudentId = newStudentId.text.toString()
            val updatedStudentName = newName.text.toString()
            val updatedSpinnerProgram = newProgram.selectedItem.toString()
            val updatedSpinnerFaculty = newFaculty.selectedItem.toString()
            val updatedNic = newNic.text.toString()
            val updatedStdPass = newPass.text.toString()
            val updatedEmail = newEmail.text.toString()
            val updatedMobile = newMobile.text.toString()

            val studentForm = StudentForm(
                studentId = updatedStudentId,
                studentName = updatedStudentName,
                spinnerProgram = updatedSpinnerProgram,
                spinnerFaculty = updatedSpinnerFaculty,
                nic = updatedNic,
                stdPass = updatedStdPass,
                email = updatedEmail,
                mobile = updatedMobile
            )

            val nicValidation = studentForm.validateNic()
            val emailValidation = studentForm.validateEmail()
            val mobileValidation = studentForm.validateMobile()

            if (
                nicValidation is ValidationResults.Valid &&
                emailValidation is ValidationResults.Valid &&
                mobileValidation is ValidationResults.Valid
            ) {


                val updatedStudent = StudentEntity(
                    studentId = updatedStudentId,
                    studentName = updatedStudentName,
                    spinnerProgram = updatedSpinnerProgram,
                    spinnerFaculty = updatedSpinnerFaculty,
                    nic = updatedNic,
                    stdPass = updatedStdPass,
                    email = updatedEmail,
                    mobile = updatedMobile
                )
                CoroutineScope(Dispatchers.IO).launch {
                    repository.updateStudent(updatedStudent)
                }
                Toast.makeText(this, "Student Successfully Updated", Toast.LENGTH_LONG).show()


                finish()
            } else {

                if (nicValidation is ValidationResults.Invalid) {
                    displayAlert("Error","Invalid NIC Format")
                }
                if (emailValidation is ValidationResults.Invalid) {
                    displayAlert("Error","Invalid Email Format")
                }
                if (mobileValidation is ValidationResults.Invalid) {
                    displayAlert("Error","Invalid Mobile Format")

                }
            }
        }
    }
}



