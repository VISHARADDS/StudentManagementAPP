package com.example.studentmanagement_it21822544

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class RegisteredViewHolder(view: View):ViewHolder(view) {
   val tvStudentId:TextView=view.findViewById(R.id.tvId)
   val tvStudentName:TextView=view.findViewById(R.id.tvGpa)
   val deleteStudent:ImageView=view.findViewById(R.id.deleteResults)
   val tvNumber:TextView=view.findViewById(R.id.tvNum)
   val updateStudent:ImageView=view.findViewById(R.id.updateResults)

}