package com.thanhnamitit.test.data.datasource.remote

import com.thanhnamitit.test.data.response.MatchesResponse
import com.thanhnamitit.test.data.response.TeamsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SportService {
    @GET("/teams")
    suspend fun getTeams(): TeamsResponse

    @GET("/teams/matches")
    suspend fun getAllMatches(): MatchesResponse

    @GET("/teams/{id}/matches")
    suspend fun getMatchesOfTeam(
        @Path("id") id: String
    ): MatchesResponse
}