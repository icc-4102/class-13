package com.example.clase13.navigation

import androidx.navigation.findNavController
import com.example.clase13.ui.MainActivity
import com.example.clase13.R
import com.example.clase13.ui.covidCase.CovidCasesFragmentDirections


class Navigator() {

    lateinit var activity: MainActivity

    fun navigateToDetail(countryId: String){
        val action = CovidCasesFragmentDirections.actionToCovidCaseDetailFragment(countryId)
        activity.findNavController(R.id.fragment_container).navigate(action)
//        val action = CovidCasesFragmentDirections.actionToCovidCaseDetailFragment(countryId)
//        activity.findNavController(R.id.fragment_container)?.navigate(action)
    }

    fun navigateToFavorites(){
        activity.findNavController(R.id.fragment_container)?.navigate(R.id.action_to_favoritesFragment)
    }

    fun navigateUp(){
        activity.findNavController(R.id.fragment_container)?.popBackStack()
    }

}