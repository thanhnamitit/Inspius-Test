package com.thanhnamitit.test.presentation.screen.teams.detail

import com.airbnb.epoxy.EpoxyController
import com.thanhnamitit.test.core.functional.Async
import com.thanhnamitit.test.domain.model.Matches
import com.thanhnamitit.test.domain.model.Team
import com.thanhnamitit.test.presentation.screen.common.MatchesListener
import com.thanhnamitit.test.presentation.screen.common.buildMatches
import com.thanhnamitit.test.teamHeader
import java.lang.ref.WeakReference

class TeamDetailController constructor(
    private val team: Team,
    private val listener: WeakReference<MatchesListener>
) : EpoxyController() {

    var matches: Async<Matches> = Async.Loading()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        teamHeader {
            id("header")
            team(this@TeamDetailController.team)
        }
        buildMatches(matches, listener.get())
    }
}