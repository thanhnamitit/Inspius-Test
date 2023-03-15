package com.thanhnamitit.test.presentation.screen.common

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyController
import com.thanhnamitit.test.R
import com.thanhnamitit.test.core.functional.Async
import com.thanhnamitit.test.domain.model.Match
import com.thanhnamitit.test.domain.model.Matches
import com.thanhnamitit.test.itemMatch
import com.thanhnamitit.test.presentation.ext.buildAsync
import com.thanhnamitit.test.presentation.screen.matches.detail.MatchDetailFragmentArgs
import com.thanhnamitit.test.textHeader

interface MatchesListener : CommonListener {
    fun onMatchSelected(match: Match)
}

fun Fragment.openMatch(match: Match) {
    findNavController().navigate(
        R.id.match_detail_fragment,
        args = MatchDetailFragmentArgs(match).toBundle()
    )
}

fun EpoxyController.buildMatches(
    async: Async<Matches>,
    listener: MatchesListener?
) {
    buildAsync(
        async,
        listener
    ) { matches ->
        fun buildMatches(headerRes: Int, matches: List<Match>) {
            textHeader {
                id("header-$headerRes")
                stringRes(headerRes)
            }
            matches.forEachIndexed { index, match ->
                itemMatch {
                    id("$index-$headerRes")
                    match(match)
                    onClick { _ -> listener?.onMatchSelected(match) }
                }
            }
        }
        buildMatches(R.string.upcoming_matches, matches.upcoming)
        buildMatches(R.string.previous_matches, matches.previous)
    }
}