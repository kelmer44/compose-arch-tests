/*
 * Copyright 2024 Sirius XM Holdings Inc. All rights reserved.
 */
package com.example.microfeatures.ui.multiple.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microfeatures.repository.SlowRepository
import com.example.microfeatures.ui.component.screen.FriendList
import com.example.microfeatures.ui.regular.RegularArchitectureViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FriendListViewModel @Inject constructor(
    slowRepository: SlowRepository
) : ViewModel() {

    val uiState = slowRepository.getFriendList(1)
        .map { RegularArchitectureViewModel.UiState.FriendList(friendList = it) }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            RegularArchitectureViewModel.UiState.FriendList(emptyList())
        )

}