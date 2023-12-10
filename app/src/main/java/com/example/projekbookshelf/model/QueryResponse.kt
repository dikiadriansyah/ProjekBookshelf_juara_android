package com.example.projekbookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class QueryResponse(
    val items: List<Book>?,
    val totalItems: Int,
    val kind: String,
)
