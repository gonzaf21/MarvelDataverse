package com.naisuapps.marveldataverse.domain.use_cases

import androidx.paging.PagingData
import com.naisuapps.marveldataverse.data.model.characters.Character
import com.naisuapps.marveldataverse.data.repository.CharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacters @Inject constructor(private val repository: CharacterRepository) {
    operator fun invoke(): Flow<PagingData<Character>> =
        repository.getAllCharacters()
}