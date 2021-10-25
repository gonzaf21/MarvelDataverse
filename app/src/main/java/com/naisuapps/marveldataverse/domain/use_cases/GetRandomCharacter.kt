package com.naisuapps.marveldataverse.domain.use_cases

import com.naisuapps.marveldataverse.data.model.characters.Character
import com.naisuapps.marveldataverse.data.repository.CharacterRepository
import javax.inject.Inject

class GetRandomCharacter @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke(): Character = repository.getRandomCharacter().first()
}