package com.thanhnamitit.test.data.usecase

import com.thanhnamitit.test.data.MatchNotifier
import com.thanhnamitit.test.data.datasource.local.dao.MatchDao
import com.thanhnamitit.test.data.datasource.local.entity.MatchEntity
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CancelNotifyMatchImplTest {

    lateinit var cancelNotifyMatchImpl: CancelNotifyMatchImpl

    @Mock
    lateinit var dao: MatchDao

    @Mock
    lateinit var matchNotifier: MatchNotifier

    @Before
    fun before() {
        cancelNotifyMatchImpl = CancelNotifyMatchImpl(dao, matchNotifier)
    }

    @Test
    fun `execute functions in dao and notifier`() = runTest {
        val match = mockMatch
        cancelNotifyMatchImpl(match)
        Mockito.verify(dao, times(1)).delete(MatchEntity.fromMatch(match))
        Mockito.verify(matchNotifier, times(1)).cancelNotifyMatch(match)
    }
}