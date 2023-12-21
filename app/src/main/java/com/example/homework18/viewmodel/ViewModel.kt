package com.example.homework18.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.homework18.dataclass.Data
import com.example.homework18.network.ApiClient
import com.example.homework18.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ViewModel(
    private val userRepository: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val flow: Flow<PagingData<Data>> = userRepository.getPerson()
        .map { pagingData -> pagingData }
        .cachedIn(viewModelScope)

    fun getData(): Flow<PagingData<Data>> {
        return flow
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val userRepository = UserRepository(apiService = ApiClient.apiService)
                ViewModel(
                    userRepository = userRepository,
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }
}



