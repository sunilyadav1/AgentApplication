package com.sunil.assignment.network

class ApiHelper(private val apiService: ApiService) {
    suspend fun getMovieList() = apiService.getMovieList()
}