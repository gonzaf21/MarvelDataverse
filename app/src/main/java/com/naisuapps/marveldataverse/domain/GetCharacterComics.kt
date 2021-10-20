package com.naisuapps.marveldataverse.domain

import com.naisuapps.marveldataverse.data.model.comics.Comic
import com.naisuapps.marveldataverse.data.repository.CharacterRepository
import dagger.assisted.Assisted
import javax.inject.Inject

class GetCharacterComics @Inject constructor(
    private val repository: CharacterRepository,
) {
    var characterId: Int = 0

    suspend operator fun invoke(): List<Comic> = repository.getCharacterComics(characterId)
}