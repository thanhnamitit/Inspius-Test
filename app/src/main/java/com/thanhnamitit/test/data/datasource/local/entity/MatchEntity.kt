package com.thanhnamitit.test.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thanhnamitit.test.domain.model.Match
import com.thanhnamitit.test.domain.model.MatchType

@Entity(tableName = "match")
data class MatchEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long?,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "readable_date") val readableDate: String,
    @ColumnInfo(name = "date_in_millis") val dateInMillis: Long,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "home") val home: String,
    @ColumnInfo(name = "away") val away: String
) {
    fun toMatch(): Match {
        return Match(
            readableDate = readableDate,
            date = date,
            dateInMillis = dateInMillis,
            description = description,
            home = home,
            away = away,
            type = MatchType.UPCOMING
        )
    }

    companion object {
        fun fromMatch(match: Match): MatchEntity {
            return MatchEntity(
                uid = match.id,
                date = match.date,
                readableDate = match.readableDate,
                home = match.home,
                away = match.away,
                description = match.description,
                dateInMillis = match.dateInMillis
            )
        }
    }
}