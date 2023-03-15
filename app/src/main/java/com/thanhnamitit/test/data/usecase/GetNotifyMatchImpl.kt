package com.thanhnamitit.test.data.usecase

import com.thanhnamitit.test.data.datasource.local.dao.MatchDao
import com.thanhnamitit.test.domain.model.Match
import com.thanhnamitit.test.domain.usecase.GetNotifyMatchUseCase
import javax.inject.Inject

class GetNotifyMatchImpl @Inject constructor(
    private val dao: MatchDao
) :
    GetNotifyMatchUseCase {
    override suspend fun invoke(id: Long): Match? {
        return dao.getMatchById(id)?.toMatch()
    }
}