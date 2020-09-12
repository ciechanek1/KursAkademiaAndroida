package com.ciechu.kursakademiaandroida.core.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciechu.kursakademiaandroida.core.exeption.ErrorMapper
import com.hadilq.liveevent.LiveEvent

open class BaseViewModel(private val errorMapper: ErrorMapper): ViewModel(), DefaultLifecycleObserver {

    private val _message by lazy { LiveEvent<String>() }

    val message: LiveData<String> by lazy { _message }

    private val _uiSate by lazy { MutableLiveData<UiState>() }

    val uiState: LiveData<UiState> by lazy { _uiSate }

    protected fun showMessage(message: String) {
        _message.value = message
    }

    protected fun setIdleState() {
        _uiSate.value = UiState.Idle
    }

    protected fun setPendingState() {
        _uiSate.value = UiState.Pending
    }

    protected fun handleFailure(throwable: Throwable){
        val errorMessage = errorMapper.map(throwable)
        showMessage(errorMessage)
    }
}