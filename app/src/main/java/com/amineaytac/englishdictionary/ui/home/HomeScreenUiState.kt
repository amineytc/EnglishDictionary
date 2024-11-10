package com.amineaytac.englishdictionary.ui.home

import com.amineaytac.englishdictionary.core.data.model.Word

data class HomeScreenUiState(
    val words: List<Word> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = ""
)