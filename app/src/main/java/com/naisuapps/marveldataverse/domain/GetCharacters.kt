package com.naisuapps.marveldataverse.domain

import com.naisuapps.marveldataverse.data.model.characters.Character
import com.naisuapps.marveldataverse.data.repository.CharacterRepository
import javax.inject.Inject

class GetCharacters @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke(): List<Character> = repository.getAllCharacters()
}