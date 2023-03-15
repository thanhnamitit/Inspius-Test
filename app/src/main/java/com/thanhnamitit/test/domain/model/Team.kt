package com.thanhnamitit.test.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val id: String,
    val name: String,
    val logo: String
) : Parcelable