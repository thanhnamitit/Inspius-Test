package com.thanhnamitit.test.data.usecase

import com.thanhnamitit.test.data.MatchNotifier
import com.thanhnamitit.test.data.datasource.local.dao.MatchDao
import com.thanhnamitit.test.data.datasource.local.entity.MatchEntity
import com.thanhnamitit.test.domain.model.Match
import com.thanhnamitit.test.domain.usecase.RequestNotifyMatchUseCase
import javax.inject.Inject

class RequestNotifyMatchImpl @Inject constructor(
    private val dao: MatchDao,
    private val matchNotifier: MatchNotifier,
) :
    RequestNotifyMatchUseCase {
    override suspend fun invoke(match: Match) {
        dao.insert(MatchEntity.fromMatch(match))
        matchNotifier.requestToNotifyMatch(match)
    }
}