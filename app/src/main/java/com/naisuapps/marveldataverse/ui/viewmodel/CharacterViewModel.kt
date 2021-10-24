package com.naisuapps.marveldataverse.ui.viewmodel

import android.util.MutableDouble
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naisuapps.marveldataverse.data.model.characters.Character
import com.naisuapps.marveldataverse.data.model.comics.Comic
import com.naisuapps.marveldataverse.domain.GetCharacterComics
import com.naisuapps.marveldataverse.domain.GetCharacters
import com.naisuapps.marveldataverse.domain.GetRandomCharacter
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharacters: GetCharacters,
    private val getCharacterComics: GetCharacterComics,
    private val getRandomCharacter: GetRandomCharacter
) : ViewModel() {
    val characterModel = MutableLiveData<Character>()
    val comicsModel = MutableLiveData<List<Comic>>()
    val isLoading = MutableLiveData<Boolean>()
    val dynamicSpanCount = MutableLiveData<Boolean>()
    lateinit var charactersList: List<Character>
    lateinit var comicsList: List<Comic>

    fun onCreate() {
        /*viewModelScope.launch {
            isLoading.postValue(true)

            // Characters
            charactersList = getCharacters()

            if (!charactersList.isNullOrEmpty()) {
                val randomCharacter = charactersList.random()
                characterModel.postValue(randomCharacter)

                // Comics
                getCharacterComics.characterId = randomCharacter.id
                comicsList = getCharacterComics()

                if (!charactersList.isNullOrEmpty()) {
                    comicsModel.postValue(comicsList)
                }
            }

            // Disable pb after loading.
            isLoading.postValue(false)
        }*/
        randomCharacter()
    }

    /**
     * Get random character from use case that calls the repository (TODO WELL WITH REPOSITORY, NOW DONE SHITTY).
     */
    fun randomCharacter() {
        viewModelScope.launch {
            isLoading.postValue(true)

            // Random character
            val randomCharacter = getRandomCharacter()
            characterModel.postValue(randomCharacter)

            // Comics
            getCharacterComics.characterId = randomCharacter.id
            comicsList = getCharacterComics()
            comicsModel.postValue(comicsList)

            isLoading.postValue(false)
        }
    }

    /**
     * Change span count of recyclerView to show more or less items inline
     */
    fun changeDynamicSpanCount() {
        val currentValue = dynamicSpanCount.value ?: false
        viewModelScope.launch {
            dynamicSpanCount.postValue(!currentValue)
        }

        Logger.i("Dynamic span count value changed to $currentValue")
    }
}