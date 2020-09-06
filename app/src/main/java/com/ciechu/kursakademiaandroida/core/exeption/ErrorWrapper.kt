package com.ciechu.kursakademiaandroida.core.exeption

interface ErrorWrapper {
    fun wrap(throwable: Throwable): Throwable
}