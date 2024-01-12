package com.example.studentmanagement_it21822544

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ResultsViewHolder(view:View):ViewHolder(view) {
    val tvNum:TextView = view.findViewById(R.id.tvNum)
    val tvId:TextView=view.findViewById(R.id.tvId)
    val tvGpa:TextView=view.findViewById(R.id.tvGpa)
    val updateResults:ImageView=view.findViewById(R.id.updateResults)
    val deleteResults:ImageView=view.findViewById(R.id.deleteResults)

}