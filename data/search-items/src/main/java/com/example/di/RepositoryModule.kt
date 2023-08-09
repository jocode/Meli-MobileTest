package com.example.di

import com.example.search_items.SearchRepository
import com.example.search_items.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl,
    ): SearchRepository

}