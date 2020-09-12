package com.ciechu.kursakademiaandroida.core.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciechu.kursakademiaandroida.core.exeption.ErrorMapper
import com.ciechu.kursakademiaandroida.core.exeption.ErrorMapperImpl
import com.ciechu.kursakademiaandroida.core.exeption.ErrorWrapper
import com.ciechu.kursakademiaandroida.core.exeption.ErrorWrapperImpl
import com.ciechu.kursakademiaandroida.core.network.NetworkStateProvider
import com.ciechu.kursakademiaandroida.core.network.NetworkStateProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    factory { LinearLayoutManager(this.androidContext()) }
    factory { GridLayoutManager(this.androidContext(), 2) }
    factory { DividerItemDecoration(this.androidContext(), LinearLayoutManager.VERTICAL)}
    factory { androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
    factory<NetworkStateProvider> { NetworkStateProviderImpl(get()) }
    factory<ErrorWrapper> { ErrorWrapperImpl() }
    factory<ErrorMapper> { ErrorMapperImpl(androidContext()) }
}