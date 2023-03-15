package com.thanhnamitit.test.presentation.screen.teams.detail

import com.thanhnamitit.test.core.base.BaseViewModel
import com.thanhnamitit.test.core.functional.Async
import com.thanhnamitit.test.domain.model.Matches
import com.thanhnamitit.test.domain.model.Team
import com.thanhnamitit.test.domain.usecase.GetMatchesOfTeamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val getMatchesOfTeamUseCase: GetMatchesOfTeamUseCase
) : BaseViewModel() {

    private val _matchesFlow = MutableStateFlow<Async<Matches>>(Async.Loading())
    val matchesFlow: StateFlow<Async<Matches>>
        get() = _matchesFlow

    fun getMatches(team: Team) = execute({ getMatchesOfTeamUseCase(team.id) }) {
        _matchesFlow.value = it
    }
}