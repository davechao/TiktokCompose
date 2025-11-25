package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.friend.FriendsUiState
import com.example.myapplication.usecase.FetchFriendsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FriendViewModel @Inject constructor(
    private val useCase: FetchFriendsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FriendsUiState())
    val uiState: StateFlow<FriendsUiState> = _uiState.asStateFlow()

    init {
        fetchFriends()
    }

    private fun fetchFriends() {
        viewModelScope.launch {
            showLoading(true)

            val friends = useCase()
            _uiState.update { state -> state.copy(friends = friends) }

            showLoading(false)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        _uiState.update { state -> state.copy(isLoading = isLoading) }
    }
}