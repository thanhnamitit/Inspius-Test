package com.thanhnamitit.test.data.usecase

import com.thanhnamitit.test.data.datasource.remote.SportService
import com.thanhnamitit.test.domain.model.Matches
import com.thanhnamitit.test.domain.usecase.GetMatchesOfTeamUseCase
import javax.inject.Inject

class GetMatchesOfTeamImpl @Inject constructor(
    private val sportService: SportService
) : GetMatchesOfTeamUseCase {
    override suspend fun invoke(id: String): Matches {
        return sportService.getMatchesOfTeam(id).toMatches()
    }
}