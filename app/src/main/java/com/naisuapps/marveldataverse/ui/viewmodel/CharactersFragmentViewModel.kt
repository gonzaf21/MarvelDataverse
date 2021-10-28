package com.naisuapps.marveldataverse.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.naisuapps.marveldataverse.data.model.ResourceState
import com.naisuapps.marveldataverse.data.model.characters.Character
import com.naisuapps.marveldataverse.domain.use_cases.GetCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersFragmentViewModel @Inject constructor(
    private val getCharacters: GetCharacters,
) : ViewModel() {
    private val _charactersState = MutableStateFlow<ResourceState<PagingData<Character>>>(ResourceState.InitialState)
    val charactersState: StateFlow<ResourceState<PagingData<Character>>> = _charactersState

    fun getCharactersData() = viewModelScope.launch {
        _charactersState.value = ResourceState.Loading
        getCharacters().cachedIn(viewModelScope).collect { pagingData ->
            _charactersState.value = ResourceState.Success(pagingData)
        }
    }
}