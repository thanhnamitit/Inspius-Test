package com.thanhnamitit.test.domain.usecase

import com.thanhnamitit.test.domain.model.Match

interface GetNotifyMatchUseCase {
    suspend operator fun invoke(id: Long): Match?
}