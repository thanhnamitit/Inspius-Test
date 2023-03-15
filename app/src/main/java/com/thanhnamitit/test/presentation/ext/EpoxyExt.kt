package com.thanhnamitit.test.presentation.ext

import com.airbnb.epoxy.EpoxyController
import com.thanhnamitit.test.core.functional.Async
import com.thanhnamitit.test.error
import com.thanhnamitit.test.loading
import com.thanhnamitit.test.presentation.screen.common.CommonListener

fun <T> EpoxyController.buildAsync(
    async: Async<T>,
    commonListener: CommonListener?,
    successBuilder: (T) -> Unit
) {
    when (async) {
        is Async.Fail -> error {
            id("error")
            exception(async.error)
            onReload { _ ->
                commonListener?.onReload()
            }
        }
        is Async.Loading -> loading {
            id("loading")
        }
        is Async.Success -> successBuilder(async.data)
    }
}