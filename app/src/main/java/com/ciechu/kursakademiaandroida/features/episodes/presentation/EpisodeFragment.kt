package com.ciechu.kursakademiaandroida.features.episodes.presentation

import android.view.View
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciechu.kursakademiaandroida.R
import com.ciechu.kursakademiaandroida.core.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_episode.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : BaseFragment<EpisodeViewModel>(R.layout.fragment_episode) {

    override val viewModel: EpisodeViewModel by viewModel()

    private val episodeAdapter: EpisodeAdapter by inject()
    private val layoutManager: LinearLayoutManager by inject()
    private val dividerItemDecoration: DividerItemDecoration by inject()

    override fun initView() {
        super.initView()
        initRecyclerView()
    }

    override fun initObserver() {
        super.initObserver()
        observeEpisodes()
    }

    override fun onIdleState() {
        super.onIdleState()
        progressBar.visibility = View.GONE
    }

    override fun onPendingState() {
        super.onPendingState()
        progressBar.visibility = View.VISIBLE
    }

    private fun observeEpisodes() {
        viewModel.episodes.observe(this) {
            episodeAdapter.setEpisodes(it)
        }
    }

    private fun initRecyclerView(){
        episodeRecyclerView.apply {
            layoutManager = this@EpisodeFragment.layoutManager
            addItemDecoration(dividerItemDecoration)
            adapter = episodeAdapter
        }
    }
}