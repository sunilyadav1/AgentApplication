package com.sunil.assignment.network


import com.sunil.agentapp.model.MovieResponse
import retrofit2.http.GET

interface ApiService {

    @GET("search?term=all")
    suspend fun getMovieList(): MovieResponse
}