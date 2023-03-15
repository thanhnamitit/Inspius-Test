package com.thanhnamitit.test.presentation.ext

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.thanhnamitit.test.R
import com.thanhnamitit.test.domain.model.Match
import com.thanhnamitit.test.domain.model.MatchType


@BindingAdapter("imageUrl")
fun ImageView.imageUrl(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

@BindingAdapter("exception")
fun TextView.exception(throwable: Throwable?) {
    text = resources.getString(R.string.error_common)
}

@BindingAdapter("stringRes")
fun TextView.stringRes(textRes: Int) {
    text = resources.getString(textRes)
}

@BindingAdapter("matchHomeName")
fun TextView.matchHomeName(match: Match) = matchTeamName(match.home, match)

@BindingAdapter("matchAwayName")
fun TextView.matchAwayName(match: Match) = matchTeamName(match.away, match)

fun TextView.matchTeamName(name: String, match: Match) {
    text = if (match.type == MatchType.PREVIOUS && name == match.winner) {
        resources.getString(R.string.winner, name)
    } else {
        name
    }
}

@BindingAdapter("visibleIf")
fun View.visibleIf(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}
