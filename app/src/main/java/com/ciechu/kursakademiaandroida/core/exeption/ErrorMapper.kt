package com.ciechu.kursakademiaandroida.core.exeption

interface ErrorMapper {
    fun map(throwable: Throwable): String
}