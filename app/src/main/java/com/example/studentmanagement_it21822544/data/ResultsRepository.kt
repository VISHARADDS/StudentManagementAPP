package com.example.studentmanagement_it21822544.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ResultsRepository(
    private val db: RoomDb
) {
    suspend fun insertResults(resultEntity: ResultEntity)= db.resultsDao()?.insertResults(resultEntity)
    suspend fun deleteResults(resultEntity: ResultEntity)= db.resultsDao()?.deleteResults(resultEntity)
    suspend fun updateResults(resultEntity: ResultEntity)= db.resultsDao()?.updateResults(resultEntity)

    fun getAllResults(): List<ResultEntity>? = db.resultsDao()?.getAllResults()

    suspend fun doesResultsExist(studentId: String): Boolean {
        return withContext(Dispatchers.IO) {
            val resultEntity = db.resultsDao()?.getResult(studentId)
            return@withContext resultEntity != null
        }
    }
    suspend fun getResult(studentId: String): ResultEntity? {
        return withContext(Dispatchers.IO) {
            db.resultsDao()?.getResult(studentId)
        }
    }

}
