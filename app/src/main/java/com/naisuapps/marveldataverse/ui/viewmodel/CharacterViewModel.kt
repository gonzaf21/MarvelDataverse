package com.naisuapps.marveldataverse.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naisuapps.marveldataverse.data.model.Character
import com.naisuapps.marveldataverse.domain.GetCharacters
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharacters: GetCharacters
) : ViewModel() {
    val characterModel = MutableLiveData<Character>()
    val isLoading = MutableLiveData<Boolean>()
    lateinit var charactersList: List<Character>

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)

            charactersList = getCharacters()

            if(!charactersList.isNullOrEmpty()) {
                characterModel.postValue(charactersList.random())
            }

            // Disable pb after loading.
            isLoading.postValue(false)
        }
    }

    /**
     * Get random character from use case that calls the repository (TODO WELL WITH REPOSITORY, NOW DONE SHITTY).
     */
    fun randomCharacter() {
        isLoading.postValue(true)
        /*val quote = getRandomQuote()

        if (quote != null) {
            quoteModel.postValue(quote)
        }*/

        if(!charactersList.isNullOrEmpty()){
            characterModel.postValue(charactersList.random())
        }

        isLoading.postValue(false)
    }
}