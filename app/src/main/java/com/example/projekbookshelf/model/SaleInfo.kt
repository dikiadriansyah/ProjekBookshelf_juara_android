package com.example.projekbookshelf.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SaleInfo(
    val country: String,
    val isEbook: Boolean,
    val listPrice: ListPrice?
): Parcelable
