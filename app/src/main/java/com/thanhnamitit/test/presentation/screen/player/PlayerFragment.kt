package com.thanhnamitit.test.presentation.screen.player

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.thanhnamitit.test.core.base.BaseFragment
import com.thanhnamitit.test.databinding.FragmentPlayerBinding


class PlayerFragment : BaseFragment<FragmentPlayerBinding>(FragmentPlayerBinding::inflate) {

    private val args by navArgs<PlayerFragmentArgs>()

    private var player: ExoPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val path = args.url
        player = createPlayer(path)
        binding.playerView.player = player
        player?.play()
    }

    private fun createPlayer(path: String): ExoPlayer {
        return player ?: ExoPlayer.Builder(requireContext()).build().apply {
            val mediaItem: MediaItem = MediaItem.fromUri(path)
            setMediaItem(mediaItem)
            prepare()
        }
    }

    override fun onDestroy() {
        player?.release()
        player = null
        super.onDestroy()
    }
}