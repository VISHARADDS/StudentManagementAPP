package com.example.studentmanagement_it21822544.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "registered_students")
data class StudentEntity(
    @PrimaryKey
    var studentId: String,
    var studentName: String,
    var spinnerProgram: String,
    var spinnerFaculty: String,
    var nic: String,
    var stdPass: String,
    var email: String,
    var mobile: String
){

}


