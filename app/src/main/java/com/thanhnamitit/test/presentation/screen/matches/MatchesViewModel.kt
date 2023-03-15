package com.thanhnamitit.test.presentation.screen.matches

import com.thanhnamitit.test.core.base.BaseViewModel
import com.thanhnamitit.test.core.functional.Async
import com.thanhnamitit.test.domain.model.Matches
import com.thanhnamitit.test.domain.usecase.GetAllMatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val getAllMatchesUseCase: GetAllMatchesUseCase
) : BaseViewModel() {

    private val _matchesFlow = MutableStateFlow<Async<Matches>>(Async.Loading())
    val matchesFlow: StateFlow<Async<Matches>>
        get() = _matchesFlow

    fun getMatches() = execute({ getAllMatchesUseCase() }) {
        _matchesFlow.value = it
    }
}