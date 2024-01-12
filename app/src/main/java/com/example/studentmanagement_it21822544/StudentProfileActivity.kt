package com.example.studentmanagement_it21822544

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.studentmanagement_it21822544.data.ResultsRepository
import com.example.studentmanagement_it21822544.data.RoomDb
import com.example.studentmanagement_it21822544.data.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentProfileActivity : AppCompatActivity() {
    private lateinit var viewModel: FragmentViewModel
    val profileFragment = ProfileFragment()
    val resultsFragment = ResultsFragment()
    val calenderFragment = CalenderFragment()
    private lateinit var studentRepository: StudentRepository
    private lateinit var resultsRepository:ResultsRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_profile)


        viewModel = ViewModelProvider(this)[FragmentViewModel::class.java]
        val profile: Button = findViewById(R.id.profile)
        val result: Button = findViewById(R.id.result)
        val events: Button = findViewById(R.id.events)
        val logout:Button= findViewById(R.id.logout)
        studentRepository = StudentRepository(RoomDb.getAppDatabase(this))
        resultsRepository= ResultsRepository(RoomDb.getAppDatabase(this))

        logout.setOnClickListener{
            val intent = Intent(this, StudentLoginActivity::class.java)
            startActivity(intent)

        }

        profile.setOnClickListener {

            loadProfile()
        }



        result.setOnClickListener {
            loadResults()

        }

        events.setOnClickListener {
            loadCalender()
        }
    }

    private fun loadProfile() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)

        if (fragment == null) {
            Log.d("StudentProfile", "Adding ProfileFragment")
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, profileFragment)
                .commit()
        } else {
            Log.d("StudentProfile", "Replacing with ProfileFragment")
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, profileFragment)
                .commit()
        }
        CoroutineScope(Dispatchers.IO).launch {
            val studentId = intent.getStringExtra("studentId") ?: "IT21822544"
            val student = studentRepository.getStudent(studentId)

            withContext(Dispatchers.Main) {
                if (student != null) {
                    viewModel.setStudent(student)
                }
                profileFragment.setStudentInfo(student)
            }
        }
    }


private fun loadResults() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)



        if (fragment == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, resultsFragment)
                .commit()

        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, resultsFragment).commit()


        }
    CoroutineScope(Dispatchers.IO).launch {
        val studentId = intent.getStringExtra("studentId") ?: "IT21822544"
        val result = resultsRepository.getResult(studentId)

        withContext(Dispatchers.Main) {
            if (result != null) {
                viewModel.setResult(result)
            }
            resultsFragment.setResults(result)
        }
    }

    }

    private fun loadCalender() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)



        if (fragment == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, calenderFragment)
                .commit()

        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, calenderFragment).commit()


        }

    }
}