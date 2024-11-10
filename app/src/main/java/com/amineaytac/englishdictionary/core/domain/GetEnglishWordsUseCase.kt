package com.amineaytac.englishdictionary.core.domain

import com.amineaytac.englishdictionary.core.common.ResponseState
import com.amineaytac.englishdictionary.core.data.model.Word
import com.amineaytac.englishdictionary.core.data.repo.DictionaryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEnglishWordsUseCase @Inject constructor(private val dictionaryRepository: DictionaryRepository) {
    suspend operator fun invoke(word: String): Flow<ResponseState<List<Word>>> {
        return dictionaryRepository.getEnglishWords(word)
    }
}