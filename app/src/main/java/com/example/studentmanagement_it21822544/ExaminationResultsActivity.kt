package com.example.studentmanagement_it21822544

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagement_it21822544.data.ResultEntity
import com.example.studentmanagement_it21822544.data.ResultsRepository
import com.example.studentmanagement_it21822544.data.RoomDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExaminationResultsActivity : AppCompatActivity() {
    lateinit var resultsAdapter: ResultsAdapter
    lateinit var viewModel: ResultsViewModel
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_examination_results)

        val resultAdd: Button = findViewById(R.id.resultAdd)
        resultAdd.setOnClickListener {
            val intent = Intent(this, AddResult::class.java)
            startActivity(intent)
        }

        val back3:Button=findViewById(R.id.back3)
        back3.setOnClickListener{
            val intent=Intent(this,StaffActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.examRecylcerView)
        viewModel = ViewModelProvider(this)[ResultsViewModel::class.java]



        fetchDataAndUpdateUI()
    }

    override fun onResume() {
        super.onResume()

        fetchDataAndUpdateUI()
    }

    private fun fetchDataAndUpdateUI() {
        val repository = ResultsRepository(RoomDb.getAppDatabase(this))

        viewModel.data.observe(this, Observer<List<ResultEntity>> { data ->
            resultsAdapter = ResultsAdapter(data, repository, viewModel)
            recyclerView.adapter = resultsAdapter
            recyclerView.layoutManager = LinearLayoutManager(this)

        })



        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getAllResults()
            runOnUiThread {
                if (data != null) {
                    viewModel.setData(data)
                }
            }
        }
    }
}
