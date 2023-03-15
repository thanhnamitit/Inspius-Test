package com.thanhnamitit.test.data

import com.thanhnamitit.test.domain.model.Match


interface MatchNotifier {
    fun requestToNotifyMatch(math: Match)
    fun cancelNotifyMatch(math: Match)
}