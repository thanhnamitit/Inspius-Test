package com.thanhnamitit.test.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanhnamitit.test.core.functional.Async
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    fun <T> execute(
        executor: suspend () -> T,
        callBack: (Async<T>) -> Unit
    ) {
        viewModelScope.launch {
            callBack.invoke(Async.Loading())
            try {
                val result = executor()
                callBack(Async.Success(result))
            } catch (e: Throwable) {
                e.printStackTrace()
                callBack(Async.Fail(e))
            }
        }
    }
}