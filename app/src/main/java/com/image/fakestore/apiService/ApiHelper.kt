package com.image.fakestore.apiService


import com.image.fakestore.ui.model.FakeStoreData
import kotlinx.coroutines.flow.Flow

interface ApiHelper {

    /**
     * Fetches a flow of FakeStoreData from the FakeStore API.
     *
     * @return A Flow emitting FakeStoreData
     */
    suspend fun getStoreItems(): Flow<List<FakeStoreData>>

}