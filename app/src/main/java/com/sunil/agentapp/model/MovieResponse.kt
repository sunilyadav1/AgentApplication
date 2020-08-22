package com.sunil.agentapp.model

data class MovieResponse(
    val resultCount: Int,
    val results: List<Result>
)