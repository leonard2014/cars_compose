package com.leonard.cars_compose.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonard.cars_compose.domain.GetCarsUseCase
import com.leonard.cars_compose.ui.model.UIState
import com.leonard.cars_compose.ui.model.toUIModel
import kotlinx.coroutines.launch

class MainViewModel(getCarsUseCase: GetCarsUseCase) : ViewModel() {

    private val _carsList = mutableStateOf<UIState>(UIState.Loading)
    val carsList: State<UIState> = _carsList

    init {
        viewModelScope.launch {
            _carsList.value = getCarsUseCase.getCars().toUIModel()
        }
    }
}
