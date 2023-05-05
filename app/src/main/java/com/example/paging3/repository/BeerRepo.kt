package com.example.paging3.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.paging3.BeerApi
import com.example.paging3.BeerDatabase
import com.example.paging3.BeerRemoteMediator
import com.example.paging3.paging.BeerPagingSource
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class BeerRepo @Inject constructor(
    private val beerApi: BeerApi,
    private val beerDatabase: BeerDatabase
) {

    fun getBeers() = Pager(
        config = PagingConfig(pageSize = 25, maxSize = 100),
        remoteMediator = BeerRemoteMediator(beerApi, beerDatabase),
        pagingSourceFactory = { beerDatabase.beerDao.getBeers() }
    ).liveData
}