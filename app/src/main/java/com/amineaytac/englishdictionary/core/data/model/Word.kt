package com.amineaytac.englishdictionary.core.data.model

data class Word(
    val word: String,
    val phonetic : String,
    val phonetics: List<Phonetic>,
    val meanings: List<Meaning>
)