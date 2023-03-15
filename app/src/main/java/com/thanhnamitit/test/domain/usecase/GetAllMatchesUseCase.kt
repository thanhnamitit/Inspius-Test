package com.thanhnamitit.test.domain.usecase

import com.thanhnamitit.test.domain.model.Matches

interface GetAllMatchesUseCase {
    suspend operator fun invoke(): Matches
}