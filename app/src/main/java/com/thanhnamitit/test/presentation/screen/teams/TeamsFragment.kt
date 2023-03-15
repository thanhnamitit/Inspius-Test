package com.thanhnamitit.test.presentation.screen.teams

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.thanhnamitit.test.R
import com.thanhnamitit.test.core.base.BaseFragment
import com.thanhnamitit.test.databinding.FragmentTeamsBinding
import com.thanhnamitit.test.domain.model.Team
import com.thanhnamitit.test.presentation.screen.teams.detail.TeamDetailFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

@AndroidEntryPoint
class TeamsFragment : BaseFragment<FragmentTeamsBinding>(FragmentTeamsBinding::inflate),
    TeamsListener {
    private val controller = TeamsController(WeakReference(this))
    private val viewModel: TeamsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.teamsFlow.value() ?: viewModel.getParticipatingTeams()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.setControllerAndBuildModels(controller)
    }

    override suspend fun collectFlow() {
        super.collectFlow()
        viewModel.teamsFlow.collect {
            controller.teams = it
        }
    }

    override fun onTeamSelected(team: Team) {
        findNavController().navigate(
            R.id.team_detail_fragment,
            args = TeamDetailFragmentArgs(team = team).toBundle()
        )
    }

    override fun onReload() {
        viewModel.getParticipatingTeams()
    }
}