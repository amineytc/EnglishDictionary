package com.amineaytac.englishdictionary.core.network.rest

import com.amineaytac.englishdictionary.core.data.model.Word
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryRestApi {

    @GET("en/{word}")
    suspend fun getEnglishWords(
        @Path("word") word: String
    ): Response<List<Word>>
}