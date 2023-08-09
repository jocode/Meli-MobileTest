package com.example.items_search.di

import com.example.items_search.ItemRepository
import com.example.items_search.ItemRepositoryImpl
import com.example.items_search.SearchRepository
import com.example.items_search.SearchRepositoryImpl
import com.example.items_search.remote.ItemApi
import com.example.items_search.remote.SearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchDataModule {

    /**
     * Network API
     */
    @Singleton
    @Provides
    fun provideSearchApi(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    @Singleton
    @Provides
    fun provideItemApi(retrofit: Retrofit): ItemApi {
        return retrofit.create(ItemApi::class.java)
    }

    /**
     * Repositories
     */
    @Singleton
    @Provides
    fun provideSearchRepository(
        searchApi: SearchApi,
    ): SearchRepository {
        return SearchRepositoryImpl(searchApi)
    }

    @Singleton
    @Provides
    fun provideItemRepository(
        itemApi: ItemApi,
    ): ItemRepository {
        return ItemRepositoryImpl(itemApi)
    }

}