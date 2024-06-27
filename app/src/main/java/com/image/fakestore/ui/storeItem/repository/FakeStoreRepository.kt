package com.image.fakestore.ui.storeItem.repository

import com.image.fakestore.apiService.ApiHelper
import com.image.fakestore.apiService.FakeStoreService
import com.image.fakestore.ui.storeItem.model.FakeStoreData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeStoreRepository @Inject constructor(private val fakeStoreService: FakeStoreService) :
    ApiHelper {

    /**
     * Retrieves a flow of FakeStore items from the FakeStore API.
     *
     * This method fetches fake store items from the FakeStore API.
     * It returns a Flow of FakeStoreResponse.
     * which can be collected asynchronously.
     *
     * @return A Flow emitting the FakeStoreData for the specified page.
     */
    override suspend fun getStoreItems(): Flow<List<FakeStoreData>> =
        flow {
            emit(fakeStoreService.getStoreItems())
        }



}