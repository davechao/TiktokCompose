package com.example.myapplication.di

import com.example.myapplication.datasource.FriendDataSource
import com.example.myapplication.datasource.FriendDataSourceImpl
import com.example.myapplication.datasource.VideoDataSource
import com.example.myapplication.datasource.VideoDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindVideoDataSource(
        impl: VideoDataSourceImpl
    ): VideoDataSource

    @Binds
    @Singleton
    abstract fun bindFriendDataSource(
        impl: FriendDataSourceImpl
    ): FriendDataSource
}