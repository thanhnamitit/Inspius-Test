package com.thanhnamitit.test.core.functional

@Suppress("UNCHECKED_CAST")
sealed class Async<T> {
    class Success<T>(val data: T) : Async<T>()
    class Loading<T>(val data: T? = null) : Async<T>()
    class Fail<T>(val error: Throwable) : Async<T>()


    operator fun invoke(): T? {
        return if (this is Success<*>) data as T else null
    }
}