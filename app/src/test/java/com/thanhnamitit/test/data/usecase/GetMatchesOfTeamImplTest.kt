package com.thanhnamitit.test.data.usecase

import com.google.common.truth.Truth
import com.thanhnamitit.test.data.datasource.remote.SportService
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
internal class GetMatchesOfTeamImplTest {
    lateinit var getMatchesOfTeamImpl: GetMatchesOfTeamImpl

    @Mock
    lateinit var service: SportService

    @Before
    fun before() {
        getMatchesOfTeamImpl = GetMatchesOfTeamImpl(service)
    }

    @Test
    fun `execute functions in service`() = runTest {
        Mockito.`when`(service.getMatchesOfTeam(anyString())).thenReturn(mockMatchResponse)
        val result = getMatchesOfTeamImpl("")
        Mockito.verify(service, Mockito.times(1)).getMatchesOfTeam("")
        Truth.assertThat(result).isEqualTo(mockMatchResponse.toMatches())
    }
}