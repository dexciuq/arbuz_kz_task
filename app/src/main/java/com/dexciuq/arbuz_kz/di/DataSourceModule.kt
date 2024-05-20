package com.dexciuq.arbuz_kz.di

import com.dexciuq.arbuz_kz.data.data_source.DataSource
import com.dexciuq.arbuz_kz.data.data_source.local.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun provideLocalDataSource(localDataSource: LocalDataSource): DataSource
}