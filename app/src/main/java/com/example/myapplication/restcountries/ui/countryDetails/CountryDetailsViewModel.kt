package com.example.myapplication.restcountries.ui.countryDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.myapplication.restcountries.domain.GetCountiesByDetailsUseCase
import com.example.myapplication.restcountries.domain.model.CountriesDomainEntity
import com.example.myapplication.restcountries.ui.model.CountriesUiModel
import com.example.myapplication.restcountries.ui.model.mapper.toUi
import com.example.myapplication.restcountries.core.network.utils.stateIn
import com.example.myapplication.restcountries.ui.CountryDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CountryDetailsViewModel @Inject constructor(
    private val getCountiesByDetailsUseCase: GetCountiesByDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val topicId = savedStateHandle.toRoute<CountryDetails>().name

    val uiState: StateFlow<CountriesUiModel> = getCountiesByDetailsUseCase(topicId)
        .map(CountriesDomainEntity::toUi)
        .stateIn(CountriesUiModel())
}