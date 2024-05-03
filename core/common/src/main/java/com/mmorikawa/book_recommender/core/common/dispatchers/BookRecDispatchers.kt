package com.mmorikawa.book_recommender.core.common.dispatchers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val bookRecDispatcher: BookRecDispatchers)

enum class BookRecDispatchers {
    Default,
    IO
}