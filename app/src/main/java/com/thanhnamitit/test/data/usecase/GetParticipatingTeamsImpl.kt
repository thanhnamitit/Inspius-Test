package com.thanhnamitit.test.data.usecase

import com.thanhnamitit.test.data.datasource.remote.SportService
import com.thanhnamitit.test.domain.model.Team
import com.thanhnamitit.test.domain.usecase.GetParticipatingTeamsUseCase
import javax.inject.Inject

class GetParticipatingTeamsImpl @Inject constructor(
    private val sportService: SportService
) : GetParticipatingTeamsUseCase {
    override suspend fun invoke(): List<Team> {
        return sportService.getTeams().teams.map { it.toModel() }
    }
}