package com.amineaytac.englishdictionary.core.data.model

data class Definition(
    val definition: String,
    val example: String,
    val synonyms: List<String> = emptyList(),
    val antonyms: List<String> = emptyList()
)