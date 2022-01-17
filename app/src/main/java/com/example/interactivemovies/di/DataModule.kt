package com.example.interactivemovies.di

import com.example.data.repositories.UserRepository
import com.example.data.source.LocalDataSource
import com.example.data.sources.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun userRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        /*permissionChecker: PermissionCheck*/
    ) = UserRepository(localDataSource, remoteDataSource)
}