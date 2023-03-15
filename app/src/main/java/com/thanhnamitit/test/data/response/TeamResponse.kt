package com.thanhnamitit.test.data.response

import com.google.gson.annotations.SerializedName
import com.thanhnamitit.test.domain.model.Team

data class TeamsResponse(
    @SerializedName("teams") val teams: List<TeamResponse>
)

data class TeamResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("logo") val logo: String
) {
    fun toModel() = Team(
        id = id,
        name = name,
        logo = logo
    )
}