package com.amineaytac.englishdictionary.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amineaytac.englishdictionary.core.common.ResponseState
import com.amineaytac.englishdictionary.core.domain.GetEnglishWordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getEnglishWordsUseCase: GetEnglishWordsUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun setSearchQuery(query: String) {
        viewModelScope.launch {
            if (query.isEmpty()) {
                clearSearchResults()
                return@launch
            }

            _uiState.value =
                _uiState.value.copy(isLoading = true, isError = false, errorMessage = null)

            getEnglishWordsUseCase(query).collect { response ->
                when (response) {
                    is ResponseState.Success -> {
                        _uiState.value = HomeScreenUiState(
                            words = response.data,
                            isLoading = false,
                            isError = false
                        )
                    }

                    is ResponseState.Error -> {
                        _uiState.value = HomeScreenUiState(
                            words = emptyList(),
                            isLoading = false,
                            isError = true,
                            errorMessage = response.message
                        )
                    }

                    is ResponseState.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    private fun clearSearchResults() {
        _uiState.value = HomeScreenUiState(
            words = emptyList(),
            isLoading = false,
            isError = false,
            errorMessage = null
        )
    }
}