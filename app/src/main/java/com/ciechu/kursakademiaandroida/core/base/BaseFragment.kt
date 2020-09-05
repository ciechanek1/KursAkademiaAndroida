package com.ciechu.kursakademiaandroida.core.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe

abstract class BaseFragment<T: BaseViewModel>(@LayoutRes layoutRes: Int): Fragment() {

    abstract val viewModel: T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
        bindViewModelToLifecycle()
    }

    open fun initView(){}

    open fun initObserver(){
        observeMessage()
        observeUiState()
    }

    open fun onIdleState(){}
    open fun onPendingState(){}

    protected fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(@StringRes stringRes: Int) {
        showToast(getString(stringRes))
    }

    private fun bindViewModelToLifecycle() {
        lifecycle.addObserver(viewModel)
    }

    private fun observeMessage() {
        viewModel.message.observe(viewLifecycleOwner) {
            showToast(it)
        }
    }

    private fun observeUiState() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                UiState.Idle -> onIdleState()
                UiState.Pending -> onPendingState()
            }
        }
    }
}