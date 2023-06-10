package com.example.ghibliapp.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ghibliapp.data.GhibliRepository
import com.example.ghibliapp.model.GetMovie
import com.example.ghibliapp.model.Ghibli
import com.example.ghibliapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: GhibliRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<GetMovie>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<GetMovie>>>
        get() = _uiState

    private val _groupedGhibli = MutableStateFlow(
        repository.getData()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupedGhibli: StateFlow<Map<Char,List<Ghibli>>> get() = _groupedGhibli

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedGhibli.value = repository.searchGhibli(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }
    fun getAllRewards() {
        viewModelScope.launch {
            repository.getAllRewards()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { ghibli ->
                    _uiState.value = UiState.Success(ghibli)
                }
        }
    }
}