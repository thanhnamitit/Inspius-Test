package com.thanhnamitit.test.data.usecase

import com.thanhnamitit.test.data.datasource.remote.SportService
import com.thanhnamitit.test.domain.model.Match
import com.thanhnamitit.test.domain.model.Matches
import com.thanhnamitit.test.domain.usecase.GetAllMatchesUseCase
import javax.inject.Inject

class GetAllMatchesImpl @Inject constructor(
    private val sportService: SportService
) : GetAllMatchesUseCase {
    override suspend fun invoke(): Matches {
        return sportService.getAllMatches().toMatches()
    }
}