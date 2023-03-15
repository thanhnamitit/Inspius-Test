package com.thanhnamitit.test.presentation.screen.teams

import com.airbnb.epoxy.EpoxyController
import com.thanhnamitit.test.core.functional.Async
import com.thanhnamitit.test.domain.model.Team
import com.thanhnamitit.test.itemTeam
import com.thanhnamitit.test.presentation.ext.buildAsync
import java.lang.ref.WeakReference

class TeamsController(
    private val listener: WeakReference<TeamsListener>
) : EpoxyController() {
    var teams: Async<List<Team>> = Async.Loading()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() = buildAsync(teams, listener.get()) { teams ->
        teams.forEach {
            itemTeam {
                id(it.id)
                team(it)
                onClick { _ ->
                    this@TeamsController.listener.get()?.onTeamSelected(it)
                }
            }
        }
    }
}