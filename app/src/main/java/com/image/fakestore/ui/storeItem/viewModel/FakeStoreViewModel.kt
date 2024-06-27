package com.image.fakestore.ui.storeItem.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.image.fakestore.ui.model.FakeStoreData
import com.image.fakestore.ui.repository.FakeStoreRepository
import com.image.fakestore.ui.viewState.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FakeStoreViewModel @Inject constructor(
    private val storeFakeRepository: FakeStoreRepository,
) : ViewModel() {

    private val _uiState = mutableStateOf<UiState>(UiState.InProgress)
    val uiState: MutableState<UiState>
        get() = _uiState

    private val _storeData = MutableStateFlow<ArrayList<FakeStoreData>>(arrayListOf())
    val storeData = _storeData.asStateFlow()

    /**
     * Fetches store items from the FakeStore API
     *
     * Launches a coroutine to fetch items from the repository.
     * The operation runs on the IO dispatcher. Exceptions and responses are logged.
     *
     */
    fun fetchStoreItem() {
        viewModelScope.launch {
            storeFakeRepository.getStoreItems()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                }
                .collect {
                    _storeData.emit(it as ArrayList<FakeStoreData>)
                    _uiState.value = UiState.StoreData
                }
        }
    }

}