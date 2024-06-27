package com.image.fakestore.di

import com.image.fakestore.apiService.FakeStoreService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@InstallIn(SingletonComponent::class)
@Module
object FakeStoreModule {

    /**
     * Provides an implementation of FakeStoreService using Retrofit.
     *
     * @param retrofit The Retrofit instance used to create the service.
     * @return An implementation of FakeStoreService.
     */
    @Provides
    fun provideNewsApiService(retrofit: Retrofit): FakeStoreService {
        return retrofit.create(FakeStoreService::class.java)
    }
}