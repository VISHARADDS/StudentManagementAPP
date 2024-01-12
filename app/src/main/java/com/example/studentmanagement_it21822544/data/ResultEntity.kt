package com.example.studentmanagement_it21822544.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey



@Entity(
    tableName = "student_semester_gpa",
    foreignKeys = [
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["studentId"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
)
class ResultEntity (
    @PrimaryKey
    @ColumnInfo(name = "studentId")
    var studentId:String,
    @ColumnInfo(name = "semester")
    var semester: String,
    @ColumnInfo(name = "module1")
    var module1: String,
    @ColumnInfo(name = "module2")
    var module2: String,
    @ColumnInfo(name = "module3")
    var module3: String,
    @ColumnInfo(name = "GPA")
    var gpa:Double,
) {

}

