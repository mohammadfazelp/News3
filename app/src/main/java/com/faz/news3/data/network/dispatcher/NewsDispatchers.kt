package com.faz.news3.data.network.dispatcher

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatchers: NewsDispatchers)

enum class NewsDispatchers {
    IO
}
