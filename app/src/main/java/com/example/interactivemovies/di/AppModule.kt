package com.example.interactivemovies.di

import android.app.Application
import com.example.data.source.LocalDataSource
import com.example.data.sources.RemoteDataSource
import com.example.interactivemovies.data.database.db.AppDataBase
import com.example.interactivemovies.data.database.db.RoomDataSource
import com.example.interactivemovies.data.server.cinepolisAPI.CinepolisAPIDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun dataBaseProvider(app: Application) = AppDataBase.getInstance(app.applicationContext)

    @Provides
    fun localDataSourceProvider(db: AppDataBase): LocalDataSource = RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = CinepolisAPIDataSource()
}