package com.example.waetherapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waetherapp.data.remote.CurrentWeatherModel
import com.example.waetherapp.data.remote.PastWeatherModel
import com.example.waetherapp.repository.DateRepository
import com.example.waetherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val dateRep: DateRepository,
    private val weatherRep: WeatherRepository
) : ViewModel() {


    fun currentWeatherData(onResult: (weatherData: CurrentWeatherModel) -> Unit) =
        viewModelScope.launch {
            val data = dateRep.getCurrentWeatherData()
            onResult(data)
        }

    private var _pastWeatherData = MutableStateFlow<List<PastWeatherModel>>(emptyList())
    val pastWeatherData: StateFlow<List<PastWeatherModel>> = _pastWeatherData

    init {
        loadPastTemperatures()
    }

    private fun loadPastTemperatures() = viewModelScope.launch {
        try {
            val data = withContext(Dispatchers.IO) {
                weatherRep.getPastData()
            }
            _pastWeatherData.value = data
        } catch (t: Throwable) {
            Log.e("WeatherViewModel", "Failed to load past weather", t)
            _pastWeatherData.value = emptyList()
        }
    }
}