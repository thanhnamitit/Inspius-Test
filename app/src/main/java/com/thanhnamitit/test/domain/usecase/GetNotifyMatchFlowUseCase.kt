package com.thanhnamitit.test.domain.usecase

import com.thanhnamitit.test.domain.model.Match
import kotlinx.coroutines.flow.Flow

interface GetNotifyMatchFlowUseCase {
    operator fun invoke(id: Long): Flow<Match?>
}