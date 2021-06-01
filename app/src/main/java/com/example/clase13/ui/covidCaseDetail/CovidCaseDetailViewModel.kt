package com.example.clase13.ui.covidCaseDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.clase13.model.CovidCaseModel

import com.example.clase13.navigation.Navigator
import com.example.clase13.repository.CovidCaseRepository


class CovidCaseDetailViewModel(
    private val covidCaseRepository: CovidCaseRepository,
    val navigator: Navigator
) : ViewModel() {

    lateinit var case:LiveData<CovidCaseModel>

    fun bindView(country: String) {
        covidCaseRepository.loadCaseFromLocation(country)
        case = covidCaseRepository.locationCountry
    }
}