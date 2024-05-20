package com.dexciuq.arbuz_kz.di

import com.dexciuq.arbuz_kz.data.repository.ProductRepositoryImpl
import com.dexciuq.arbuz_kz.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
}