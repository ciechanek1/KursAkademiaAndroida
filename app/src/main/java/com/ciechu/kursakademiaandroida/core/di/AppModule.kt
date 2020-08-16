package com.ciechu.kursakademiaandroida.core.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciechu.kursakademiaandroida.core.network.NetworkStateProvider
import com.ciechu.kursakademiaandroida.core.network.NetworkStateProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    factory<RecyclerView.LayoutManager> { LinearLayoutManager(androidContext()) }
    factory { DividerItemDecoration(androidContext(), LinearLayoutManager.VERTICAL)}
    factory { androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
    factory<NetworkStateProvider> { NetworkStateProviderImpl(get()) }
}