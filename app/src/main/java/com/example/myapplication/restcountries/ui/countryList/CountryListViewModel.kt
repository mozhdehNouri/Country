package com.example.myapplication.restcountries.ui.countryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.network_wrapper.AppResult
import com.example.myapplication.restcountries.core.di.Dispatcher
import com.example.myapplication.restcountries.core.di.RestCountries
import com.example.myapplication.restcountries.core.network.utils.stateIn
import com.example.myapplication.restcountries.domain.FetchAllCountriesFromNetworkUseCase
import com.example.myapplication.restcountries.domain.GetAllCountriesFromLocalUseCase
import com.example.myapplication.restcountries.ui.model.CountriesUiModel
import com.example.myapplication.restcountries.ui.model.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val fetchAllCountriesFromNetworkUseCase: FetchAllCountriesFromNetworkUseCase,
    private val getAllCountriesFromLocalUseCase: GetAllCountriesFromLocalUseCase,
    @Dispatcher(RestCountries.IO) private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val getCountries: StateFlow<CountryListUiState> = getAllCountriesFromLocalUseCase().map {
        CountryListUiState.Success(it.toUi())
    }.stateIn(CountryListUiState.Loading)

    private val _oneTimeEvent = Channel<ShowError>(Channel.BUFFERED)
    val event = _oneTimeEvent.receiveAsFlow()

    init {
        loadCountryData()
    }

    private fun loadCountryData() {
        viewModelScope.launch(ioDispatcher) {
            when (val result = fetchAllCountriesFromNetworkUseCase()){
                is AppResult.Error -> {
                    _oneTimeEvent.send(ShowError.ShowToast(result.error.toString()))
                }
                is AppResult.Success -> {
                    /// auto update
                }
            }

        }

    }
}

sealed interface CountryListUiState {
    data object Loading : CountryListUiState

    data class Success(
        val countryList: List<CountriesUiModel>,
    ) : CountryListUiState
}

sealed interface ShowError {
    data class ShowToast(val message: String) : ShowError
}