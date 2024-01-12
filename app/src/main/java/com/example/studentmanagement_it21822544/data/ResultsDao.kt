package com.example.studentmanagement_it21822544.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface ResultsDao {
    @Query("SELECT * FROM student_semester_gpa ORDER BY studentId ASC ")
    fun getAllResults():List<ResultEntity>


    @Insert
   suspend fun insertResults(resultsEntity: ResultEntity)

    @Update
    suspend fun updateResults(resultsEntity: ResultEntity)

    @Delete
    suspend fun deleteResults(resultsEntity: ResultEntity)

    @Query("SELECT * FROM student_semester_gpa WHERE studentId=:studentId")
    fun getResult(studentId:String):ResultEntity

}

