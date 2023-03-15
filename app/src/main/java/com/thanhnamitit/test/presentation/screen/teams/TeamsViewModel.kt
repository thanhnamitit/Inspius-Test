package com.thanhnamitit.test.presentation.screen.teams

import com.thanhnamitit.test.core.base.BaseViewModel
import com.thanhnamitit.test.core.functional.Async
import com.thanhnamitit.test.domain.model.Team
import com.thanhnamitit.test.domain.usecase.GetParticipatingTeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val getParticipatingTeamsUseCase: GetParticipatingTeamsUseCase
) : BaseViewModel() {
    private val _teamsFlow = MutableStateFlow<Async<List<Team>>>(Async.Loading())
    val teamsFlow: StateFlow<Async<List<Team>>>
        get() = _teamsFlow

    fun getParticipatingTeams() = execute({ getParticipatingTeamsUseCase() }) {
        _teamsFlow.value = it
    }
}