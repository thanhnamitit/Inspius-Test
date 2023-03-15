package com.thanhnamitit.test.data.usecase

import com.thanhnamitit.test.data.MatchNotifier
import com.thanhnamitit.test.data.datasource.local.dao.MatchDao
import com.thanhnamitit.test.data.datasource.local.entity.MatchEntity
import com.thanhnamitit.test.domain.model.Match
import com.thanhnamitit.test.domain.usecase.CancelNotifyMatchUseCase
import javax.inject.Inject

class CancelNotifyMatchImpl @Inject constructor(
    private val dao: MatchDao,
    private val matchNotifier: MatchNotifier,
) : CancelNotifyMatchUseCase {
    override suspend fun invoke(match: Match) {
        dao.delete(MatchEntity.fromMatch(match))
        matchNotifier.cancelNotifyMatch(match)
    }
}