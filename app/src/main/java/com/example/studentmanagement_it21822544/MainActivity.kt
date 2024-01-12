package com.example.studentmanagement_it21822544

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stdBtn: Button =findViewById(R.id.stdBtn)
        stdBtn.setOnClickListener{
            val intent= Intent(this,StudentLoginActivity::class.java)
            startActivity(intent)
        }
        val staffBtn: Button =findViewById(R.id.staffBtn)
        staffBtn.setOnClickListener{
            val intent= Intent(this,StaffActivity::class.java)
            startActivity(intent)
        }





    }
}