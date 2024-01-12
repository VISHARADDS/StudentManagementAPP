package com.example.studentmanagement_it21822544

import android.content.Intent
import android.graphics.drawable.ClipDrawable.VERTICAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagement_it21822544.data.RoomDb
import com.example.studentmanagement_it21822544.data.StudentEntity
import com.example.studentmanagement_it21822544.data.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisteredStudentsActivity : AppCompatActivity() {

    lateinit var registeredAdapter: RegisteredAdapter
    lateinit var viewModel: StudentViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registered_students)

        val back2:Button=findViewById(R.id.back2)
        back2.setOnClickListener{
            val intent=Intent(this,StaffActivity::class.java)
            startActivity(intent)
        }

        val recyclerView:RecyclerView=findViewById(R.id.recyclerView)
        val repository = StudentRepository(RoomDb.getAppDatabase(this))
        viewModel=ViewModelProvider(this)[StudentViewModel::class.java]
        viewModel.data.observe(this, Observer<List<StudentEntity>> { data ->
            registeredAdapter = RegisteredAdapter(data, repository, viewModel)
            recyclerView.adapter = registeredAdapter
            recyclerView.layoutManager = LinearLayoutManager(this)
        })

        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getAllStudentInfo()
            runOnUiThread {
                if (data != null) {
                    viewModel.setData(data)
                }
            }
        }



    }
}




 /*
         recyclerView = findViewById(R.id.recyclerView)


        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@RegisteredStudentsActivity)
            registeredAdapter=RegisteredAdapter(this@RegisteredStudentsActivity)
            adapter=registeredAdapter
            val divider=DividerItemDecoration(applicationContext,VERTICAL)
            addItemDecoration(divider)
        }
        viewModel= ViewModelProvider(this).get(StudentViewModel::class.java)
        viewModel.getAllStudentsObservers().observe(this, Observer {
            registeredAdapter.setListData(ArrayList(it))
            registeredAdapter.notifyDataSetChanged()
        })
    }

    override fun onDeleteStudentClickListener(studentEntity: StudentEntity) {
        viewModel.deleteStudentInfo(studentEntity)
    }

    override fun onItemClickListener(studentEntity: StudentEntity) {
        TODO("Not yet implemented")
    }
}

  */