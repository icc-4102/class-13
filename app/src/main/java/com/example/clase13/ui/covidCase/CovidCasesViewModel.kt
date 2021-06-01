package com.example.clase13.ui.covidCase


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import com.example.clase13.model.CovidCaseModel
import com.example.clase13.navigation.Navigator
import com.example.clase13.repository.CovidCaseRepository


class CovidCasesViewModel(
    val covidCaseRepository: CovidCaseRepository,
    val navigator: Navigator
) : ViewModel() {

    lateinit var cases: LiveData<List<CovidCaseModel>>

    fun bindView(){
        cases = covidCaseRepository.cases
    }

    fun loadCaseFromLocation(country: String) {
        covidCaseRepository.loadCaseFromLocation(country)
    }

    fun selectCase(item: CovidCaseModel) {
        covidCaseRepository.selectedCase.postValue(item)
        navigator.navigateToDetail(item.country)
    }



}