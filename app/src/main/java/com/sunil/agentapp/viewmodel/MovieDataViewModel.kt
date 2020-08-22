package com.sunil.agentapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mindorks.retrofit.coroutines.utils.Resource
import com.sunil.agentapp.repository.MovieDataRepository
import kotlinx.coroutines.Dispatchers

class MovieDataViewModel (private val mainRepository: MovieDataRepository) : ViewModel() {

    fun getMovieList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getMovieList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}