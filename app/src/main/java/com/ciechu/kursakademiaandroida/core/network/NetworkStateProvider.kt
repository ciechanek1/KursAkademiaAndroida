package com.ciechu.kursakademiaandroida.core.network

interface NetworkStateProvider {
    fun isNetworkAvailable(): Boolean
}