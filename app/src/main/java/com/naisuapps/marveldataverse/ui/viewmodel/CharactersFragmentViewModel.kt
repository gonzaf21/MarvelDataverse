package com.naisuapps.marveldataverse.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.naisuapps.marveldataverse.domain.use_cases.GetCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersFragmentViewModel @Inject constructor(
    private val getCharacters: GetCharacters,
) : ViewModel() {
    val flowCharacters = getCharacters.invoke()
        .cachedIn(viewModelScope)
}