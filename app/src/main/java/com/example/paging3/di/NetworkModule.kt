package com.example.paging3.di

import android.content.Context
import androidx.room.Room
import com.example.paging3.BeerApi
import com.example.paging3.BeerDatabase
import com.example.paging3.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getBeerApi(retrofit: Retrofit): BeerApi {
        return retrofit.create(BeerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBeerDataBase(@ApplicationContext context : Context) : BeerDatabase {
        return Room.databaseBuilder(
            context,
            BeerDatabase::class.java,
            "beers.db"
        ).build()
    }

}