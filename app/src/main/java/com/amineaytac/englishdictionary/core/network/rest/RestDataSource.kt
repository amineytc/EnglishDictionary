package com.amineaytac.englishdictionary.core.network.rest

import com.amineaytac.englishdictionary.core.data.model.Word
import retrofit2.Response

interface RestDataSource {
    suspend fun getEnglishWords(word:String): Response<List<Word>>
}