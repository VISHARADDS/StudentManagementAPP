package com.example.studentmanagement_it21822544.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StudentRepository(
    private val db: RoomDb
) {
    suspend fun insertStudent(studentEntity:StudentEntity)= db.studentDao()?.insertStudent(studentEntity)
    suspend fun deleteStudent(studentEntity:StudentEntity)= db.studentDao()?.deleteStudent(studentEntity)
    suspend fun  updateStudent(studentEntity:StudentEntity)= db.studentDao()?.updateStudent(studentEntity)

    fun getAllStudentInfo(): List<StudentEntity>? = db.studentDao()?.getAllStudentInfo()

    suspend fun getStudent(studentId: String): StudentEntity? {
        return withContext(Dispatchers.IO) {
            db.studentDao()?.getStudent(studentId)
        }
    }
    fun doesStudentExist(studentId: String): Boolean {
        val student = db.studentDao()?.getStudent(studentId)
        return student != null
    }

}