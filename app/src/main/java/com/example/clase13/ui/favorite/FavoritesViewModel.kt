package com.example.clase13.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.clase13.model.CovidCaseModel
import com.example.clase13.navigation.Navigator

import com.example.clase13.repository.CovidCaseRepository

class FavoritesViewModel(val covidCaseRepository: CovidCaseRepository,
val navigator: Navigator
): ViewModel() {

    lateinit var favoritesCases: LiveData<List<CovidCaseModel>>

    fun bindView(){
        favoritesCases = covidCaseRepository.cases
    }
}