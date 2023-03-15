package com.thanhnamitit.test.data.usecase

import com.google.common.truth.Truth.assertThat
import com.thanhnamitit.test.data.datasource.remote.SportService
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class GetAllMatchesImplTest {
    lateinit var getAllMatchImpl: GetAllMatchesImpl

    @Mock
    lateinit var service: SportService

    @Before
    fun before() {
        getAllMatchImpl = GetAllMatchesImpl(service)
    }

    @Test
    fun `execute functions in service`() = runTest {
        Mockito.`when`(service.getAllMatches()).thenReturn(mockMatchResponse)
        val result = getAllMatchImpl()
        Mockito.verify(service, Mockito.times(1)).getAllMatches()
        assertThat(result).isEqualTo(mockMatchResponse.toMatches())
    }
}