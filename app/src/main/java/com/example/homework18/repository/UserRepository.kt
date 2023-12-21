package com.example.homework18.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.homework18.dataclass.Data
import com.example.homework18.paging.PagingSource
import com.example.homework18.service.ApiService
import kotlinx.coroutines.flow.Flow

class UserRepository(private val apiService: ApiService) {
    fun getPerson(): Flow<PagingData<Data>> {
        return Pager(PagingConfig(pageSize = 1)) {
            PagingSource(apiService)
        }.flow
    }
}