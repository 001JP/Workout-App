package dev.x001.workoutapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDAO {

    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    @Update
    suspend fun update(historyEntity: HistoryEntity)

    @Query("SELECT * FROM `history-table`")
    fun fetchAllRecords(): Flow<List<HistoryEntity>>

    @Query("SELECT * FROM `history-table` where id=:id")
    fun fetchRecordById(id:Int) :Flow<HistoryEntity>
}