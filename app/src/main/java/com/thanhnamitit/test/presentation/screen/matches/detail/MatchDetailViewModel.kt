package com.thanhnamitit.test.presentation.screen.matches.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.thanhnamitit.test.core.base.BaseViewModel
import com.thanhnamitit.test.domain.model.Match
import com.thanhnamitit.test.domain.usecase.CancelNotifyMatchUseCase
import com.thanhnamitit.test.domain.usecase.GetNotifyMatchFlowUseCase
import com.thanhnamitit.test.domain.usecase.RequestNotifyMatchUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun provideFactory(
    assistedFactory: MatchDetailViewModelFactory,
    match: Match,
): ViewModelProvider.Factory =
    object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return assistedFactory.create(match) as T
        }
    }

@AssistedFactory
interface MatchDetailViewModelFactory {
    fun create(match: Match): MatchDetailViewModel
}

class MatchDetailViewModel @AssistedInject constructor(
    @Assisted private val match: Match,
    private val requestNotifyMatchUseCase: RequestNotifyMatchUseCase,
    private val cancelNotifyMatchUseCase: CancelNotifyMatchUseCase,
    getNotifyMatchFlowUseCase: GetNotifyMatchFlowUseCase
) : BaseViewModel() {

    val matchFlow = getNotifyMatchFlowUseCase(match.id)

    fun requestToNotifyTheMatch() = viewModelScope.launch(Dispatchers.IO) {
        requestNotifyMatchUseCase(match)
    }

    fun cancelNotifyTheMatch() = viewModelScope.launch(Dispatchers.IO) {
        cancelNotifyMatchUseCase(match)
    }
}
