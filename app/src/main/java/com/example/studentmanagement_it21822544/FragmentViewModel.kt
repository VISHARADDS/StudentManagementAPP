package com.example.studentmanagement_it21822544

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentmanagement_it21822544.data.ResultEntity
import com.example.studentmanagement_it21822544.data.StudentEntity

class FragmentViewModel:ViewModel() {
    private val _studentLiveData = MutableLiveData<StudentEntity>()
    private val _resultLiveData = MutableLiveData<ResultEntity>()

    val studentLiveData: LiveData<StudentEntity> = _studentLiveData
    val resultLiveData: LiveData<ResultEntity> = _resultLiveData

    fun setStudent(student: StudentEntity) {
        _studentLiveData.value = student
    }

    fun setResult(result: ResultEntity) {
        _resultLiveData.value = result
    }
}