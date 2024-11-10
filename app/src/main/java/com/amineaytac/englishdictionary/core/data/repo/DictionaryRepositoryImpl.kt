package com.amineaytac.englishdictionary.core.data.repo

import com.amineaytac.englishdictionary.core.common.ResponseState
import com.amineaytac.englishdictionary.core.data.model.Word
import com.amineaytac.englishdictionary.core.network.rest.RestDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(private val restDataSource:RestDataSource
):DictionaryRepository{

    override suspend fun getEnglishWords(word:String): Flow<ResponseState<List<Word>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = restDataSource.getEnglishWords(word)
            emit(ResponseState.Success(response.mapTo { it.body().orEmpty() }))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }
}