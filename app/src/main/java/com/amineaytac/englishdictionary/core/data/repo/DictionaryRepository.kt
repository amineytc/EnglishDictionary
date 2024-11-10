package com.amineaytac.englishdictionary.core.data.repo

import com.amineaytac.englishdictionary.core.common.ResponseState
import com.amineaytac.englishdictionary.core.data.model.Word
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {
    suspend fun getEnglishWords(word:String): Flow<ResponseState<List<Word>>>
}