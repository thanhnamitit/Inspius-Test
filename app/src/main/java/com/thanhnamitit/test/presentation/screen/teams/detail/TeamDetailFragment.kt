package com.thanhnamitit.test.presentation.screen.teams.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.thanhnamitit.test.core.base.BaseFragment
import com.thanhnamitit.test.databinding.FragmentTeamDetailBinding
import com.thanhnamitit.test.domain.model.Match
import com.thanhnamitit.test.presentation.screen.common.MatchesListener
import com.thanhnamitit.test.presentation.screen.common.openMatch
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

@AndroidEntryPoint
class TeamDetailFragment : BaseFragment<FragmentTeamDetailBinding>(
    FragmentTeamDetailBinding::inflate
), MatchesListener {

    private val args by navArgs<TeamDetailFragmentArgs>()
    private val viewModel by viewModels<TeamDetailViewModel>()
    private val teamDetailController by lazy {
        TeamDetailController(
            team = args.team,
            listener = WeakReference(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.matchesFlow.value() ?: viewModel.getMatches(args.team)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.setControllerAndBuildModels(teamDetailController)
    }

    override suspend fun collectFlow() {
        super.collectFlow()
        viewModel.matchesFlow.collect {
            teamDetailController.matches = it
        }
    }

    override fun onMatchSelected(match: Match) {
        openMatch(match)
    }

    override fun onReload() {
        viewModel.getMatches(args.team)
    }
}