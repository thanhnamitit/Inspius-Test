package com.thanhnamitit.test.data.usecase

import com.thanhnamitit.test.data.datasource.local.dao.MatchDao
import com.thanhnamitit.test.data.datasource.local.entity.MatchEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetNotifyMatchFlowImplTest {

    lateinit var getNotifyMatchFlowImpl: GetNotifyMatchFlowImpl

    @Mock
    lateinit var dao: MatchDao

    @Before
    fun before() {
        getNotifyMatchFlowImpl = GetNotifyMatchFlowImpl(dao)
    }

    @Test
    fun `execute functions in dao and notifier`() = runTest {
        val match = mockMatch
        val shareFlow = MutableStateFlow(MatchEntity.fromMatch(match))
        Mockito.`when`(dao.getMatchFlowById(anyLong())).thenReturn(shareFlow)
        getNotifyMatchFlowImpl(0)
        Mockito.verify(dao, Mockito.times(1)).getMatchFlowById(0)
    }
}