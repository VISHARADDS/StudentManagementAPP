package com.example.studentmanagement_it21822544

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.studentmanagement_it21822544.data.StudentEntity
import com.example.studentmanagement_it21822544.data.StudentRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RegisteredAdapter(items:List<StudentEntity>,repository: StudentRepository, viewModel:StudentViewModel):Adapter<RegisteredViewHolder>(){

    var context:Context?=null
    val items=items
    val repository = repository
    val viewModel=viewModel
    var studentNumbers: MutableList<Int> = mutableListOf()
    init {

        for (i in 1..items.size) {
            studentNumbers.add(i)
        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisteredViewHolder {

        val view=LayoutInflater.from(parent.context).inflate(R.layout.recycleview_row,parent,false)
       context=parent.context

        return RegisteredViewHolder(view)

    }

    override fun getItemCount(): Int {
     return items.size
    }

    override fun onBindViewHolder(holder: RegisteredViewHolder, position: Int) {

        holder.tvStudentId.text= items[position].studentId
        holder.tvStudentName.text=items[position].studentName
        holder.tvNumber.text = studentNumbers[position].toString()
        holder.updateStudent.setOnClickListener{
            val student = items[position]
            val intent = Intent(context, UpdateStudentActivity::class.java)
            intent.putExtra("studentId", student.studentId)
            intent.putExtra("studentName", student.studentName)
            intent.putExtra("spinnerProgram",student.spinnerProgram)
            intent.putExtra("spinnerFaculty",student.spinnerFaculty)
            intent.putExtra("nic",student.nic)
            intent.putExtra("stdPass",student.stdPass)
            intent.putExtra("email",student.email)
            intent.putExtra("mobile",student.mobile)
            context?.startActivity(intent)



        }

        holder.deleteStudent.setOnClickListener {
            val deletedStudentId = items[position].studentId

            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Confirm Deletion")
            alertDialog.setMessage("Do you want to delete $deletedStudentId?")
            alertDialog.setPositiveButton("DELETE") { dialog, which ->
                CoroutineScope(Dispatchers.IO).launch {
                    repository.deleteStudent(items[position])
                    studentNumbers.removeAt(position)
                    val data = repository.getAllStudentInfo()
                    withContext(Dispatchers.Main) {
                        if (data != null) {
                            viewModel.setData(data)
                        }
                    }
                }
                Toast.makeText(context, "$deletedStudentId deleted", Toast.LENGTH_SHORT).show()
            }
            alertDialog.setNegativeButton("CANCEL") { dialog, which ->
                // User canceled the deletion
            }
            alertDialog.show()
        }
    }
}

/*
class RegisteredAdapter(val listener: RowClickListener):RecyclerView.Adapter<RegisteredAdapter.MyViewHolder>(){

    private var items=ArrayList<StudentEntity>()

    fun setListData(data:ArrayList<StudentEntity>){
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisteredAdapter.MyViewHolder {
       val inflater= LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycleview_row, parent, false)
        return MyViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: RegisteredAdapter.MyViewHolder, position: Int) {

        holder.itemView.setOnClickListener{
            listener.onItemClickListener(items[position])
        }
       holder.bind(items[position])
    }

 class MyViewHolder(view: View, val listener: RowClickListener):RecyclerView.ViewHolder(view){
     val tvStudentName = view.findViewById<TextView>(R.id.tvStudentName)
     val tvStudentId = view.findViewById<TextView>(R.id.tvStudentId)
     val deleteStudent = view.findViewById<ImageView>(R.id.deleteStudent)


    fun bind(data:StudentEntity){
        tvStudentName.text = data.studentName
        tvStudentId.text = data.studentId
        deleteStudent.setOnClickListener{
            listener.onDeleteStudentClickListener(data)
        }
    }
  }
    interface RowClickListener{
        fun onDeleteStudentClickListener(studentEntity: StudentEntity)
        fun onItemClickListener(studentEntity: StudentEntity)
    }
}

 */