package com.example.studentmanagement_it21822544

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.studentmanagement_it21822544.data.RoomDb
import com.example.studentmanagement_it21822544.data.StudentRepository
import com.example.studentmanagement_it21822544.models.LoginForm
import com.example.studentmanagement_it21822544.models.validations.ValidationResults
import kotlinx.coroutines.*



class StudentLoginActivity : AppCompatActivity() {
    lateinit var username: EditText
    lateinit var password: EditText
    private var count = 0
    private lateinit var studentId: String

    private lateinit var studentRepository: StudentRepository
    private lateinit var coroutineScope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        studentRepository = StudentRepository(RoomDb.getAppDatabase(this))

        val cancelBtn: Button = findViewById(R.id.cancelBtn)
        cancelBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        studentId = username.text.toString()

        coroutineScope = CoroutineScope(Dispatchers.Main + Job())


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

    fun showWelcomeMessage(message: String) {
        val toastDurationInMilliSeconds = 5000 // 5 seconds
        val toast = Toast.makeText(this@StudentLoginActivity, message, Toast.LENGTH_SHORT)
        toast.show()

        val handler = Handler()
        handler.postDelayed({ toast.cancel() }, toastDurationInMilliSeconds.toLong())
    }



    fun submit(view: View) {
        val enteredStudentId = username.text.toString()
        val enteredPassword = password.text.toString()

        coroutineScope.launch(Dispatchers.IO) {
            val myLogin = LoginForm(
                enteredStudentId,
                enteredPassword,
                studentRepository,
                enteredStudentId
            )

            val usernameValidation = myLogin.validateUsername()
            val passwordValidation = myLogin.validatePassword()

            withContext(Dispatchers.Main) {

                when (usernameValidation) {
                    is ValidationResults.Valid -> {
                        count++
                    }

                    is ValidationResults.Invalid -> {
                        username.error = usernameValidation.errorMessage
                    }

                    is ValidationResults.Empty -> {
                        username.error = usernameValidation.errorMessage
                    }
                }

                when (passwordValidation) {
                    is ValidationResults.Valid -> {
                        count++
                    }

                    is ValidationResults.Invalid -> {
                        password.error = passwordValidation.errorMessage
                    }

                    is ValidationResults.Empty -> {
                        password.error = passwordValidation.errorMessage
                    }
                }

                if (count == 2) {
                    val student = studentRepository.getStudent(enteredStudentId)

                    if (student != null) {
                        val intent = Intent(this@StudentLoginActivity, StudentProfileActivity::class.java)
                        intent.putExtra("studentId", enteredStudentId)
                        startActivity(intent)

                        val welcomeMessage = "Welcome, $enteredStudentId!"
                        showWelcomeMessage(welcomeMessage)
                        username.text.clear()
                        password.text.clear()
                    } else {
                        displayAlert("Error", "Student not found")
                    }
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()

        coroutineScope.cancel()
    }
}
