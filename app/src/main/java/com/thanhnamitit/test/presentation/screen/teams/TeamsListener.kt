package com.thanhnamitit.test.presentation.screen.teams

import com.thanhnamitit.test.domain.model.Team
import com.thanhnamitit.test.presentation.screen.common.CommonListener

interface TeamsListener : CommonListener {
    fun onTeamSelected(team: Team)
}