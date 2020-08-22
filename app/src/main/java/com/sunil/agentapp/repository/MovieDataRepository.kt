package com.sunil.agentapp.repository

import com.sunil.assignment.network.ApiHelper

class MovieDataRepository (private val apiHelper: ApiHelper) {

    suspend fun getMovieList() = apiHelper.getMovieList()
}