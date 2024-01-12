package com.example.studentmanagement_it21822544

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagement_it21822544.data.ResultEntity
import com.example.studentmanagement_it21822544.data.ResultsRepository
import com.example.studentmanagement_it21822544.data.RoomDb
import com.example.studentmanagement_it21822544.data.StudentRepository
import com.example.studentmanagement_it21822544.models.GPACalculator
import com.example.studentmanagement_it21822544.models.ResultForm
import com.example.studentmanagement_it21822544.models.validations.ValidationResults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AddResult : AppCompatActivity() {
    lateinit var stdId: EditText
    lateinit var spnSemester: Spinner
    lateinit var module1: EditText
    lateinit var module2: EditText
    lateinit var module3: EditText
    private lateinit var resultsRepository: ResultsRepository
    private lateinit var studentRepository: StudentRepository

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_result)

        studentRepository = StudentRepository(RoomDb.getAppDatabase(this))
        resultsRepository = ResultsRepository(RoomDb.getAppDatabase(this))

        stdId = findViewById(R.id.stdId)
        spnSemester = findViewById(R.id.spnSemester)
        module1 = findViewById(R.id.module1)
        module2 = findViewById(R.id.module2)
        module3 = findViewById(R.id.module3)

        val back6:Button=findViewById(R.id.back6)
        back6.setOnClickListener{
            val intent=Intent(this,ExaminationResultsActivity::class.java )
            startActivity(intent)
        }

        val calGpa: Button = findViewById(R.id.calGpa)

        fun displayAlert(title: String, message: String) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton("Ok") { dialog, which ->

            }
            val dialog = builder.create()
            dialog.show()
        }



        calGpa.setOnClickListener {
            val studentId = stdId.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val studentExists = studentRepository.doesStudentExist(studentId)
                if (!studentExists) {
                    withContext(Dispatchers.Main) {
                        displayAlert(
                            "Student Not Found",
                            "Student with ID $studentId does not exist."
                        )
                    }
                } else {

                    val resultsExist = resultsRepository.doesResultsExist(studentId)
                    if (resultsExist) {
                        withContext(Dispatchers.Main) {
                            displayAlert(
                                "Results Already Entered",
                                "Results for student ID $studentId already exist."
                            )
                        }
                    } else {

                        val resultForm = ResultForm(
                            studentId,
                            spnSemester.selectedItem.toString(),
                            module1.text.toString(),
                            module2.text.toString(),
                            module3.text.toString(),
                        )


                        val studentIdValidation = resultForm.validateStudentId()
                        val semesterValidation = resultForm.validateSemester()
                        val module1Validation = resultForm.validateModule1()
                        val module2Validation = resultForm.validateModule2()
                        val module3Validation = resultForm.validateModule3()

                        if (studentIdValidation is ValidationResults.Valid &&
                            semesterValidation is ValidationResults.Valid &&
                            module1Validation is ValidationResults.Valid &&
                            module2Validation is ValidationResults.Valid &&
                            module3Validation is ValidationResults.Valid
                        ) {
                            val gpa = GPACalculator.calculateSemesterGPA(
                                module1.text.toString(),
                                module2.text.toString(),
                                module3.text.toString()
                            )

                            val resultEntity = ResultEntity(
                                studentId,
                                spnSemester.selectedItem.toString(),
                                module1.text.toString(),
                                module2.text.toString(),
                                module3.text.toString(),
                                gpa
                            )

                            CoroutineScope(Dispatchers.IO).launch {
                                resultsRepository.insertResults(resultEntity)
                                withContext(Dispatchers.Main) {
                                    displayAlert(
                                        "Calculated GPA",
                                        "Student ID: $studentId GPA is $gpa"
                                    )
                                    stdId.text.clear()
                                    spnSemester.setSelection(0)
                                    module1.text.clear()
                                    module2.text.clear()
                                    module3.text.clear()
                                }
                            }
                        } else {

                            if (studentIdValidation is ValidationResults.Invalid) {
                                stdId.error = studentIdValidation.errorMessage
                            }
                            if (semesterValidation is ValidationResults.Empty) {
                                val tv: TextView = spnSemester.selectedView as TextView
                                tv.error = ""
                                tv.text = semesterValidation.errorMessage

                            }
                            if (module1Validation is ValidationResults.Empty) {
                                module1.error = module1Validation.errorMessage

                            }
                            if (module2Validation is ValidationResults.Empty) {
                                module2.error = module2Validation.errorMessage

                            }
                            if (module3Validation is ValidationResults.Empty) {
                                module3.error = module3Validation.errorMessage

                            }

                        }
                    }
                }
            }
        }
    }
}





