package com.ciechu.kursakademiaandroida.core.base

sealed class UiState {
    object Idle: UiState()
    object Pending: UiState()
}