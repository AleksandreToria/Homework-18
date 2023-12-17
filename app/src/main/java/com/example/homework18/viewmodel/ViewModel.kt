package com.example.homework18.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.homework18.dataclass.Data
import com.example.homework18.network.ApiClient
import com.example.homework18.paging.PagingSource
import kotlinx.coroutines.flow.Flow

class ViewModel : ViewModel() {
    private val flow = Pager(
        PagingConfig(pageSize = 20)
    ) {
        PagingSource(ApiClient.apiService)
    }.flow
        .cachedIn(viewModelScope)

    fun getData(): Flow<PagingData<Data>> {
        return flow
    }
}

