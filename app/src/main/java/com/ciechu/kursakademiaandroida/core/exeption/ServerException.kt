package com.ciechu.kursakademiaandroida.core.exeption

sealed class ServerException(message: String?): Throwable(message) {
    class Internal(message: String?): ServerException(message)
    class BadRequest(message: String?): ServerException(message)
    class Unknown(message: String?): ServerException(message)

}