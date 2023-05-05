package com.example.paging3

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.paging3.models.Beer
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val beerApi: BeerApi,
    private val beerDatabase: BeerDatabase
) : RemoteMediator<Int, Beer>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Beer>): MediatorResult {

        return try {

            val loadKey = when (loadType) {
                REFRESH -> 1
                PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                APPEND -> {
                    val lastItem = state.lastItemOrNull()

                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val beers = beerApi.getBeers(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            beerDatabase.withTransaction {
                if (loadType == REFRESH) {
                    beerDatabase.beerDao.clearAll()
                }

                beerDatabase.beerDao.insertAll(beers)
            }

            MediatorResult.Success(
                endOfPaginationReached = beers.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}