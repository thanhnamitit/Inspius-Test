package com.thanhnamitit.test.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thanhnamitit.test.data.datasource.local.dao.MatchDao
import com.thanhnamitit.test.data.datasource.local.entity.MatchEntity

@Database(
    entities = [
        MatchEntity::class,
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}
