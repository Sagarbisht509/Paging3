package com.example.paging3.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.paging3.BeerApi
import com.example.paging3.paging.BeerPagingSource
import javax.inject.Inject

class BeerRepo @Inject constructor(private val beerApi: BeerApi) {

    fun getBeers() = Pager(
        config = PagingConfig(pageSize = 25, maxSize = 100),
        pagingSourceFactory = { BeerPagingSource(beerApi) }
    ).liveData
}