package com.thanhnamitit.test.domain.usecase

import com.thanhnamitit.test.domain.model.Matches

interface GetMatchesOfTeamUseCase {
    suspend operator fun invoke(id: String): Matches
}