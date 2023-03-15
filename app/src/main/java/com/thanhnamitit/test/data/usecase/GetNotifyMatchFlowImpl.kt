package com.thanhnamitit.test.data.usecase

import com.thanhnamitit.test.data.datasource.local.dao.MatchDao
import com.thanhnamitit.test.domain.model.Match
import com.thanhnamitit.test.domain.usecase.GetNotifyMatchFlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotifyMatchFlowImpl @Inject constructor(
    private val dao: MatchDao
) : GetNotifyMatchFlowUseCase {
    override fun invoke(id: Long): Flow<Match?> {
        return dao.getMatchFlowById(id).map { it?.toMatch() }
    }
}