package com.ciechu.kursakademiaandroida.features.episodes.presentation

import androidx.lifecycle.observe
import com.ciechu.kursakademiaandroida.R
import com.ciechu.kursakademiaandroida.core.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class EpisodeFragment : BaseFragment<EpisodeViewModel>(R.layout.fragment_episode) {

    override val viewModel: EpisodeViewModel by viewModel()

    override fun initView() {
        super.initView()
        // initialize all view-related classes
    }

    override fun initObserver() {
        super.initObserver()
        observeEpisodes()
    }

    override fun onIdleState() {
        super.onIdleState()
        //handle idle state here
    }

    override fun onPendingState() {
        super.onPendingState()
        //handle pending state here
    }

    private fun observeEpisodes() {
        viewModel.episodes.observe(this) {
            //code to display episodes
        }
    }


}