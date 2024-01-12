package com.example.studentmanagement_it21822544.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {

    @Query("SELECT * FROM registered_students ORDER BY studentId ASC")
    fun getAllStudentInfo():List<StudentEntity>

    @Insert
    fun insertStudent(studentEntity: StudentEntity)

    @Delete
    fun deleteStudent(studentEntity: StudentEntity)

    @Update
    fun updateStudent(studentEntity: StudentEntity)

    @Query("SELECT * FROM registered_students WHERE studentId=:studentId")
    fun getStudent(studentId:String):StudentEntity

}