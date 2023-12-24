package com.mohamedfathidev.accuweather.ui.forecasting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamedfathidev.accuweather.data.remote.model.CurrentWeather
import com.mohamedfathidev.accuweather.domain.usecase.ForecastingUseCase
import com.mohamedfathidev.accuweather.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastingViewModel
@Inject constructor(
    private val forecastingUseCase: ForecastingUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<ResponseState<CurrentWeather>?>(null)
    val state: StateFlow<ResponseState<CurrentWeather>?> = _state

    fun getForecastedWeather() {
        viewModelScope.launch {
            _state.value = ResponseState.Loading()
            val result = forecastingUseCase()
            try {
                result.collect { currentWeather ->
                    _state.value = ResponseState.Success(currentWeather)
                    currentWeather.location?.country.let {
                        Log.d("TAG getCurrentWeather", "getCurrentWeather: $it")
                    }
                }
            } catch (e: Exception) {
                _state.value = ResponseState.Error(e.message.toString())
            }
        }
    }

}