package com.thanhnamitit.test.domain.usecase

import com.thanhnamitit.test.domain.model.Match

interface CancelNotifyMatchUseCase {
    suspend operator fun invoke(match: Match)
}