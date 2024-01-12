package com.example.studentmanagement_it21822544


import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagement_it21822544.data.ResultEntity
import com.example.studentmanagement_it21822544.data.ResultsRepository
import com.example.studentmanagement_it21822544.models.GPACalculator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ResultsAdapter(items:List<ResultEntity>,repository: ResultsRepository,viewModel: ResultsViewModel): RecyclerView.Adapter<ResultsViewHolder>() {
    var context: Context?=null
    val items = items
    val repository=repository
    var count: MutableList<Int> = mutableListOf()
    init {

        for (i in 1..items.size) {
            count.add(i)
        }
    }

    val viewModal=viewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exam_results_row,parent,false)
        context=parent.context
        return ResultsViewHolder(view)

    }

    override fun getItemCount(): Int {
        return items.size

    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {

        holder.tvNum.text=count[position].toString()
        holder.tvId.text=items[position].studentId
        holder.tvGpa.text= items[position].gpa.toString()
        holder.updateResults.setOnClickListener {
            showEditDialog(items[position])
        }

        holder.deleteResults.setOnClickListener {
            val deletedStudentId = items[position].studentId

            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Confirm Deletion")
            alertDialog.setMessage("Do you want to delete Results of $deletedStudentId?")
            alertDialog.setPositiveButton("DELETE") { dialog, which ->
                CoroutineScope(Dispatchers.IO).launch {
                    repository.deleteResults(items[position])
                    count.removeAt(position)
                    val data = repository.getAllResults()
                    withContext(Dispatchers.Main) {
                        if (data != null) {
                            viewModal.setData(data)
                        }
                    }
                }
                Toast.makeText(context, "Results of $deletedStudentId deleted", Toast.LENGTH_SHORT).show()
            }
            alertDialog.setNegativeButton("CANCEL") { dialog, which ->

            }
            alertDialog.show()
        }
    }

    private fun showEditDialog(resultEntity: ResultEntity) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.edit_results, null)
        val editModule1 = dialogView.findViewById<EditText>(R.id.editModule1)
        val editModule2 = dialogView.findViewById<EditText>(R.id.editModule2)
        val editModule3 = dialogView.findViewById<EditText>(R.id.editModule3)
        val saveButton = dialogView.findViewById<Button>(R.id.saveButton)

        // Set the initial values in the dialog
        editModule1.setText(resultEntity.module1)
        editModule2.setText(resultEntity.module2)
        editModule3.setText(resultEntity.module3)

        val dialog = AlertDialog.Builder(context)
            .setTitle(" ${resultEntity.studentId}")
            .setView(dialogView)
            .setCancelable(true)
            .create()

        saveButton.setOnClickListener {
            val updatedModule1 = editModule1.text.toString()
            val updatedModule2 = editModule2.text.toString()
            val updatedModule3 = editModule3.text.toString()


            val updatedGpa = GPACalculator.calculateSemesterGPA(updatedModule1, updatedModule2, updatedModule3)

            resultEntity.module1 = updatedModule1
            resultEntity.module2 = updatedModule2
            resultEntity.module3 = updatedModule3
            resultEntity.gpa = updatedGpa


            CoroutineScope(Dispatchers.IO).launch {
                repository.updateResults(resultEntity)
                val data = repository.getAllResults()
                withContext(Dispatchers.Main) {
                    if (data != null) {
                        viewModal.setData(data)
                    }
                }
            }


            dialog.dismiss()
        }

        dialog.show()
    }
    }


