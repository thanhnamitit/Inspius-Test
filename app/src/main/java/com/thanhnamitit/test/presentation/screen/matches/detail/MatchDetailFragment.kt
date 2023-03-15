package com.thanhnamitit.test.presentation.screen.matches.detail

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.thanhnamitit.test.R
import com.thanhnamitit.test.core.base.BaseFragment
import com.thanhnamitit.test.databinding.FragmentMatchDetailBinding
import com.thanhnamitit.test.presentation.ext.hasPostNotificationPermission
import com.thanhnamitit.test.presentation.screen.player.PlayerFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MatchDetailFragment : BaseFragment<FragmentMatchDetailBinding>(
    FragmentMatchDetailBinding::inflate
) {
    private var taskAfterPermissionRequest: (() -> Unit)? = null;

    private val requestPermissions = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        if (it.values.all { it }) taskAfterPermissionRequest?.invoke()
    }


    private val args by navArgs<MatchDetailFragmentArgs>()
    private val highlightUrl: String?
        get() = args.match.highlights

    @Inject
    lateinit var viewModelFactory: MatchDetailViewModelFactory

    private val viewModel by viewModels<MatchDetailViewModel> {
        provideFactory(viewModelFactory, args.match)
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMatchDetailBinding {
        return super.onCreateBinding(inflater, container).apply {
            match = this@MatchDetailFragment.args.match
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnWatchHighlight.setOnClickListener {
            highlightUrl?.let {
                findNavController().navigate(
                    R.id.player_fragment,
                    args = PlayerFragmentArgs(url = it).toBundle()
                )
            }
        }
        binding.btnNotify.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (!requireContext().hasPostNotificationPermission()) {
                    requestPermissions.launch(
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS)
                    )
                    taskAfterPermissionRequest = {
                        viewModel.requestToNotifyTheMatch()
                    }
                    return@setOnClickListener
                }
            }
            viewModel.requestToNotifyTheMatch()
        }
        binding.btnCancelNotify.setOnClickListener {
            viewModel.cancelNotifyTheMatch()
        }
    }

    override suspend fun collectFlow() {
        super.collectFlow()
        viewModel.matchFlow.collect {
            binding.notified = it
        }
    }
}