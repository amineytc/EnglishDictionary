package com.amineaytac.englishdictionary.core.network.rest

import com.amineaytac.englishdictionary.core.data.model.Word
import retrofit2.Response
import javax.inject.Inject

class RestDataSourceImpl @Inject constructor(private val dictionaryRestApi: DictionaryRestApi) :
    RestDataSource {
    override suspend fun getEnglishWords(word: String): Response<List<Word>> {
        return dictionaryRestApi.getEnglishWords(word)
    }

}