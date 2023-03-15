package com.thanhnamitit.test.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.thanhnamitit.test.data.datasource.local.entity.MatchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDao {
    @Query("SELECT * FROM match WHERE uid = :uid LIMIT 1")
    fun getMatchFlowById(uid: Long): Flow<MatchEntity?>

    @Query("SELECT * FROM match WHERE uid = :uid LIMIT 1")
    suspend fun getMatchById(uid: Long): MatchEntity?

    @Insert
    suspend fun insert(input: MatchEntity)

    @Delete
    suspend fun delete(input: MatchEntity)
}