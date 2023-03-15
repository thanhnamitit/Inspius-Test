package com.thanhnamitit.test.data.usecase

import com.google.common.truth.Truth.assertThat
import com.thanhnamitit.test.data.MatchNotifier
import com.thanhnamitit.test.data.datasource.local.dao.MatchDao
import com.thanhnamitit.test.data.datasource.local.entity.MatchEntity
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetNotifyMatchImplTest {

    lateinit var getNotifyMatchImpl: GetNotifyMatchImpl

    @Mock
    lateinit var dao: MatchDao

    @Before
    fun before() {
        getNotifyMatchImpl = GetNotifyMatchImpl(dao)
    }

    @Test
    fun `execute functions in dao and notifier`() = runTest {
        val match = mockMatch
        Mockito.`when`(dao.getMatchById(anyLong())).thenReturn(MatchEntity.fromMatch(match))
        val result = getNotifyMatchImpl(0)
        Mockito.verify(dao, Mockito.times(1)).getMatchById(0)
        assertThat(result).isEqualTo(match)
    }
}