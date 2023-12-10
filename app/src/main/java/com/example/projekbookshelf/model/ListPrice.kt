package com.example.projekbookshelf.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListPrice(
    val amount: Float?,
    val currency: String? = ""
):Parcelable
