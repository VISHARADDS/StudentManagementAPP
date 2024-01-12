package com.example.studentmanagement_it21822544


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentmanagement_it21822544.data.StudentEntity

class StudentViewModel:ViewModel() {
    private val _data = MutableLiveData<List<StudentEntity>>()
    val data:LiveData<List<StudentEntity>> = _data
    fun setData(data:List<StudentEntity>){
        _data.value = data
    }
}







/*

class StudentViewModel(app:Application):AndroidViewModel(app) {
    lateinit var allStudents:MutableLiveData<List<StudentEntity>>

    init {
       allStudents= MutableLiveData()
        getAllStudent()
    }

    fun getAllStudentsObservers():MutableLiveData<List<StudentEntity>>{
        return allStudents
    }

    fun getAllStudent(){
        val studentDao= RoomDb.getAppDatabase(getApplication())?.studentDao()
        val list=  studentDao?.getAllStudentInfo()


        allStudents.postValue(list)

    }

    fun insertStudentInfo(studentEntity: StudentEntity){
        val studentDao=  RoomDb.getAppDatabase(getApplication())?.studentDao()
        studentDao?.insertStudent(studentEntity)
        getAllStudent()


    }

    fun updateStudentInfo(studentEntity: StudentEntity){
        val studentDao=  RoomDb.getAppDatabase(getApplication())?.studentDao()
        studentDao?.updateStudent(studentEntity)
        getAllStudent()


    }

    fun deleteStudentInfo(studentEntity: StudentEntity){
        val studentDao=  RoomDb.getAppDatabase(getApplication())?.studentDao()
        studentDao?.deleteStudent(studentEntity)
        getAllStudent()


    }


}
 */