package com.naisuapps.marveldataverse.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.naisuapps.marveldataverse.data.network.CharacterService
import com.naisuapps.marveldataverse.data.paging.CharacterPagingSource
import com.naisuapps.marveldataverse.data.repository.CharacterRepository
import com.naisuapps.marveldataverse.domain.GetCharacters
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersFragmentViewModel @Inject constructor(
    private val getCharacters: GetCharacters,
) : ViewModel() {
    val flowCharacters = getCharacters.invoke()
        .cachedIn(viewModelScope)
}