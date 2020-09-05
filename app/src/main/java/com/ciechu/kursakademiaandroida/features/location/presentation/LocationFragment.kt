package com.ciechu.kursakademiaandroida.features.location.presentation

import android.view.View
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.ciechu.kursakademiaandroida.R
import com.ciechu.kursakademiaandroida.core.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_location.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationFragment : BaseFragment<LocationViewModel>(R.layout.fragment_location) {

    override val viewModel: LocationViewModel by viewModel()

    private val locationAdapter: LocationAdapter by inject()
    private val layoutManager: GridLayoutManager by inject()
    private val dividerItemDecoration: DividerItemDecoration by inject()

    override fun initView() {
        super.initView()
        initRecyclerView()
    }

    override fun initObserver() {
        super.initObserver()
        observeLocation()
    }

    override fun onIdleState() {
        super.onIdleState()
        progressBar.visibility = View.GONE    }

    override fun onPendingState() {
        super.onPendingState()
        progressBar.visibility = View.VISIBLE
    }

    private fun observeLocation() {
        viewModel.location.observe(this){
            locationAdapter.setLocation(it)
        }
    }
    private fun initRecyclerView(){
        locationRecyclerView.apply {
            layoutManager = this@LocationFragment.layoutManager
            addItemDecoration(dividerItemDecoration)
            adapter = locationAdapter
        }
    }
}