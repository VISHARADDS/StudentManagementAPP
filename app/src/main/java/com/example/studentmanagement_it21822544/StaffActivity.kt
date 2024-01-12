package com.example.studentmanagement_it21822544

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StaffActivity : AppCompatActivity() {



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff)

        val back4:Button=findViewById(R.id.back4)
        back4.setOnClickListener{
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


        val addStudent: Button =findViewById(R.id.addStudent)
        addStudent.setOnClickListener{
            val intent= Intent(this,AddStudentActivity::class.java)
            startActivity(intent)
        }

        val viewStudent: Button =findViewById(R.id.viewStudent)
        viewStudent.setOnClickListener{
            val intent= Intent(this,RegisteredStudentsActivity::class.java)
            startActivity(intent)
        }
        val exam: Button =findViewById(R.id.exam)
        exam.setOnClickListener{
            val intent= Intent(this,ExaminationResultsActivity::class.java)
            startActivity(intent)
        }
    }
}