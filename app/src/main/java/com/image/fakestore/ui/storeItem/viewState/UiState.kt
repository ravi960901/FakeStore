package com.image.fakestore.ui.storeItem.viewState

sealed class UiState {
    object StoreData : UiState()
    object InProgress : UiState()
}