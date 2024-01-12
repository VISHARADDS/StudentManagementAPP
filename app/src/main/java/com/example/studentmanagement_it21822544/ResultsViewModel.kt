package com.example.studentmanagement_it21822544

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentmanagement_it21822544.data.ResultEntity
import com.example.studentmanagement_it21822544.data.StudentEntity

class ResultsViewModel:ViewModel() {
    private val _data =MutableLiveData<List<ResultEntity>>()
    val data:LiveData<List<ResultEntity>> = _data

    fun setData(data: List<ResultEntity>){
        _data.value= data
    }
}