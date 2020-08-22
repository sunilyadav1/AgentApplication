package com.sunil.assignment.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sunil.agentapp.repository.MovieDataRepository
import com.sunil.agentapp.viewmodel.MovieDataViewModel
import com.sunil.assignment.network.ApiHelper

class ViewModelFactory (private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDataViewModel::class.java)) {
            return MovieDataViewModel(MovieDataRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}