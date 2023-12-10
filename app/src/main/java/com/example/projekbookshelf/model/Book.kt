package com.example.projekbookshelf.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val id: String,
    val description: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo
): Parcelable