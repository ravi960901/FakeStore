package com.image.fakestore.ui.viewState

sealed class UiState {
    object StoreData : UiState()
    object InProgress : UiState()
}