package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.event.UiEvent
import com.example.myapplication.ui.friend.FriendUiState
import com.example.myapplication.usecase.FetchFriendsUseCase
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
class FriendViewModel @Inject constructor(
    private val useCase: FetchFriendsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FriendUiState())
    val uiState: StateFlow<FriendUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    init {
        fetchFriends()
    }

    private fun fetchFriends() {
        viewModelScope.launch {
            try {
                showLoading(true)
                val friends = useCase()
                _uiState.update { it.copy(friends = friends) }
            } catch (e: Exception) {
                _events.emit(UiEvent.ShowToast(e.message ?: "Unknown Error"))
            } finally {
                showLoading(false)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        _uiState.update { state -> state.copy(isLoading = isLoading) }
    }
}