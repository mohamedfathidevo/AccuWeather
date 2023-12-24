package com.mohamedfathidev.accuweather.ui.current_weather

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamedfathidev.accuweather.data.remote.model.CurrentWeather
import com.mohamedfathidev.accuweather.domain.usecase.CurrentWeatherUseCase
import com.mohamedfathidev.accuweather.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel
@Inject constructor(
    private val currentWeatherUseCase: CurrentWeatherUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<ResponseState<Any>?>(null)
    val state: StateFlow<ResponseState<Any>?> = _state

    fun getCurrentWeather() = viewModelScope.launch {
        _state.value = ResponseState.Loading()
        val result = currentWeatherUseCase()
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