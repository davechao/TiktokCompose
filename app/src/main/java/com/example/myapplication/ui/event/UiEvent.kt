package com.example.myapplication.ui.event

sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()
}