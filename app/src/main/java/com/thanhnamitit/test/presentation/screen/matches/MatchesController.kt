package com.thanhnamitit.test.presentation.screen.matches

import com.airbnb.epoxy.EpoxyController
import com.thanhnamitit.test.core.functional.Async
import com.thanhnamitit.test.domain.model.Matches
import com.thanhnamitit.test.presentation.screen.common.MatchesListener
import com.thanhnamitit.test.presentation.screen.common.buildMatches
import java.lang.ref.WeakReference

class MatchesController constructor(
    private val listener: WeakReference<MatchesListener>
) : EpoxyController() {
    var matches: Async<Matches> = Async.Loading()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() = buildMatches(matches, listener.get())
}