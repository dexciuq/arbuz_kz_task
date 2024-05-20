package com.dexciuq.arbuz_kz.di

import android.content.Context
import androidx.room.Room
import com.dexciuq.arbuz_kz.data.data_source.local.db.AppDatabase
import com.dexciuq.arbuz_kz.data.data_source.local.db.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context = context.applicationContext,
            klass = AppDatabase::class.java,
            name = AppDatabase.DATABASE_NAME,
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }
}