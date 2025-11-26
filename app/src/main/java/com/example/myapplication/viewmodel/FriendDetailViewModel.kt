package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.firenddetail.FriendDetailUiState
import com.example.myapplication.usecase.FetchFriendUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendDetailViewModel @Inject constructor(
    private val useCase: FetchFriendUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FriendDetailUiState())
    val uiState: StateFlow<FriendDetailUiState> = _uiState.asStateFlow()

    fun fetchFriend(id: Int) {
        viewModelScope.launch {
            showLoading(true)

            val friend = useCase(id)
            _uiState.update { state -> state.copy(friend = friend) }

            showLoading(false)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        _uiState.update { state -> state.copy(isLoading = isLoading) }
    }
}