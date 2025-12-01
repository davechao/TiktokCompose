package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.event.UiEvent
import com.example.myapplication.ui.reels.ReelsUiState
import com.example.myapplication.usecase.FetchReelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReelsViewModel @Inject constructor(
    private val useCase: FetchReelsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReelsUiState())
    val uiState: StateFlow<ReelsUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    init {
        fetchReels()
    }

    private fun fetchReels() {
        viewModelScope.launch {
            try {
                val videos = useCase()
                _uiState.update { state -> state.copy(videos = videos) }
            } catch (e: Exception) {
                _events.emit(UiEvent.ShowToast(e.message ?: "Unknown Error"))
            }
        }
    }
}