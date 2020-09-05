 package com.ciechu.kursakademiaandroida.core.di

import org.koin.core.module.Module

val koinInjector: List<Module> = featuresModule
    .plus(networkModule)
    .plus(appModule)
    .plus(databaseModule)


