package com.ciechu.kursakademiaandroida.features.location.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.ciechu.kursakademiaandroida.core.base.BaseViewModel
import com.ciechu.kursakademiaandroida.core.exeption.ErrorMapper
import com.ciechu.kursakademiaandroida.features.location.domain.GetLocationUseCase
import com.ciechu.kursakademiaandroida.features.location.domain.model.Location
import com.ciechu.kursakademiaandroida.features.location.presentation.model.LocationDisplayable

class LocationViewModel(private val locationUseCase: GetLocationUseCase,
                        errorMapper: ErrorMapper
): BaseViewModel(errorMapper) {

    private val _location by lazy {
        MutableLiveData<List<Location>>()
            .also { getLocation(it) }
    }

    val location: LiveData<List<LocationDisplayable>> by lazy {
        _location.map { location ->
            location.map { LocationDisplayable(it) }
        }
    }

    private fun getLocation(locationLiveData: MutableLiveData<List<Location>>) {
        setPendingState()
        locationUseCase(
            params = Unit,
            scope = viewModelScope
        ) { result ->
            setIdleState()
            result.onSuccess { locationLiveData.value = it }
            result.onFailure { handleFailure(it) }
        }
    }
}