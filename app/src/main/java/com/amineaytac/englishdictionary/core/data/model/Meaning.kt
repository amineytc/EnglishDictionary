package com.amineaytac.englishdictionary.core.data.model

data class Meaning(
    val partOfSpeech: String,
    val definitions: List<Definition>,
    val synonyms: List<String> = emptyList(),
    val antonyms: List<String> = emptyList()
)