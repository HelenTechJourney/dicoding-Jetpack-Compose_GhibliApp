package com.example.ghibliapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ghibliapp.data.GhibliRepository
import com.example.ghibliapp.model.GetMovie
import com.example.ghibliapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: GhibliRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<GetMovie>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<GetMovie>>
        get() = _uiState

    fun getMovieById(movieId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getMovieById(movieId))
        }
    }
}