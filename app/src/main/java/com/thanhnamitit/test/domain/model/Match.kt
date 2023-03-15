package com.thanhnamitit.test.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

enum class MatchType {
    PREVIOUS,
    UPCOMING
}

data class Matches(
    val upcoming: List<Match>,
    val previous: List<Match>
)

@Parcelize
data class Match(
    val date: String,
    val readableDate: String,
    val dateInMillis: Long,
    val description: String,
    val home: String,
    val away: String,
    val type: MatchType,
    val winner: String? = null,
    val highlights: String? = null,
) : Parcelable {
    val isOver
        get() = type == MatchType.PREVIOUS

    val id: Long
        get() = hashCode().toLong()
}