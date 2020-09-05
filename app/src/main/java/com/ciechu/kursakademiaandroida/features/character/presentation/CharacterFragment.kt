package com.ciechu.kursakademiaandroida.features.character.presentation

import android.view.View
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciechu.kursakademiaandroida.R
import com.ciechu.kursakademiaandroida.core.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_character.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment : BaseFragment<CharacterViewModel>(R.layout.fragment_character) {

    override val viewModel: CharacterViewModel by viewModel()

    private val characterAdapter: CharacterAdapter by inject()
    private val layoutManager: LinearLayoutManager by inject()
    private val dividerItemDecoration: DividerItemDecoration by inject()

    override fun initView() {
        super.initView()
        initRecyclerView()
    }

    override fun initObserver() {
        super.initObserver()
        observeCharacter()
    }

    override fun onIdleState() {
        super.onIdleState()
        progressBar.visibility = View.GONE
    }

    override fun onPendingState() {
        super.onPendingState()
        progressBar.visibility = View.VISIBLE
    }

    private fun observeCharacter() {
        viewModel.character.observe(this) {
            characterAdapter.setCharacters(it)
        }
    }

    private fun initRecyclerView() {
        characterRecyclerView.apply {
            layoutManager = this@CharacterFragment.layoutManager
            addItemDecoration(dividerItemDecoration)
            adapter = characterAdapter
        }
    }
}