package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.usecase.FetchReelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val useCase: FetchReelsUseCase,
) : ViewModel() {

    init {
        fetchReels()
    }

    private fun fetchReels() {
        viewModelScope.launch {
            useCase().collectLatest {
                Timber.d("@@data: $it")
            }
        }
    }
}