package com.thanhnamitit.test.presentation.screen.matches

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.thanhnamitit.test.core.base.BaseFragment
import com.thanhnamitit.test.databinding.FragmentMatchesBinding
import com.thanhnamitit.test.domain.model.Match
import com.thanhnamitit.test.presentation.screen.common.MatchesListener
import com.thanhnamitit.test.presentation.screen.common.openMatch
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

@AndroidEntryPoint
class MatchesFragment : BaseFragment<FragmentMatchesBinding>(FragmentMatchesBinding::inflate),
    MatchesListener {

    private val viewModel by viewModels<MatchesViewModel>()
    private val controller = MatchesController(WeakReference(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.matchesFlow.value() ?: viewModel.getMatches()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.setControllerAndBuildModels(controller)
    }

    override suspend fun collectFlow() {
        super.collectFlow()
        viewModel.matchesFlow.collect {
            controller.matches = it
        }
    }

    override fun onMatchSelected(match: Match) {
        openMatch(match)
    }

    override fun onReload() {
        viewModel.getMatches()
    }
}