package com.example.projekbookshelf.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VolumeInfo(
    val title: String,
    val subtitle: String,
    val description: String,
    val imageLinks: ImageLinks? = null,
    val authors: List<String>,
    val publisher: String,
    val publisherDate: String
): Parcelable{
    val allAuthorsx: String
        get() = allAuthors()

    fun allAuthors(): String{
        var x = ""
        for(author in authors){
            x += "$author, "
        }
        return x.trimEnd(',',' ')
    }
}