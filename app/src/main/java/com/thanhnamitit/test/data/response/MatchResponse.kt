package com.thanhnamitit.test.data.response

import com.google.gson.annotations.SerializedName
import com.thanhnamitit.test.domain.model.Match
import com.thanhnamitit.test.domain.model.MatchType
import com.thanhnamitit.test.domain.model.Matches
import java.text.SimpleDateFormat
import java.util.*

data class MatchesResponse(
    @SerializedName("matches") val matches: PreviousAndUpcomingWrapper
) {
    fun toMatches(): Matches {
        return Matches(
            previous = matches.previous.map { it.toModel(MatchType.PREVIOUS) },
            upcoming = matches.upcoming.map { it.toModel(MatchType.UPCOMING) }
        )
    }
}

data class PreviousAndUpcomingWrapper(
    @SerializedName("previous") val previous: List<MatchResponse>,
    @SerializedName("upcoming") val upcoming: List<MatchResponse>
)

data class MatchResponse(
    @SerializedName("date") val date: String,
    @SerializedName("description") val description: String,
    @SerializedName("home") val home: String,
    @SerializedName("away") val away: String,
    @SerializedName("winner") val winner: String?,
    @SerializedName("highlights") val highlights: String?
) {
    fun toModel(type: MatchType): Match {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val dateTime = sdf.parse(date)

        val dateInMillis = dateTime.time

        val readableDate = SimpleDateFormat.getDateTimeInstance(
            SimpleDateFormat.MEDIUM,
            SimpleDateFormat.SHORT,
            Locale.getDefault()
        ).format(dateInMillis)

        return Match(
            date = date,
            description = description,
            home = home,
            away = away,
            winner = winner,
            highlights = highlights,
            type = type,
            readableDate = readableDate,
            dateInMillis = dateInMillis
        )
    }
}
