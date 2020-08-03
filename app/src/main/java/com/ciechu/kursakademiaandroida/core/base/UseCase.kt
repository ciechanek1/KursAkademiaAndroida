package com.ciechu.kursakademiaandroida.core.base

import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> {

    abstract suspend fun action(params: Params): Type

    operator fun invoke(scope: CoroutineScope,
                        params: Params,
                        executionDispatcher: CoroutineDispatcher = Dispatchers.IO,
                        onResults: (Result<Type>) -> Unit = {}) {
        scope.launch {
            val results = withContext(executionDispatcher) {
                runCatching { action(params) }
            }
            onResults(results)
        }
    }
}