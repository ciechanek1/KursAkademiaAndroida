package com.ciechu.kursakademiaandroida.core.di

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    factory {
        LinearLayoutManager(androidContext())
    }
    factory {
        GridLayoutManager(androidContext(), 2)
    }
    factory {
        DividerItemDecoration(androidContext(), LinearLayoutManager.VERTICAL)
    }
}