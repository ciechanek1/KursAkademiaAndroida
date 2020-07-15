package com.ciechu.kursakademiaandroida.core.di

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single {
        LinearLayoutManager(androidContext())
    }
    single {
        GridLayoutManager(androidContext(), 2)
    }
    single {
        DividerItemDecoration(androidContext(), LinearLayoutManager.VERTICAL)
    }
}