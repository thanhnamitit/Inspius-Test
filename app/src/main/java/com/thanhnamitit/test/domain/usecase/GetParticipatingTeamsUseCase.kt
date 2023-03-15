package com.thanhnamitit.test.domain.usecase

import com.thanhnamitit.test.domain.model.Team

interface GetParticipatingTeamsUseCase {
    suspend operator fun invoke(): List<Team>
}