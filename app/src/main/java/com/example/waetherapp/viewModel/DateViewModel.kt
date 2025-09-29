package com.example.waetherapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.waetherapp.repository.DateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DateViewModel @Inject constructor(
    private val dateRepository: DateRepository
) : ViewModel(){

    fun strDay() = dateRepository.getToDay()
}