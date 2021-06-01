package com.example.clase13.modules

import androidx.room.Room
import com.example.clase13.model.AppDatabase
import com.example.clase13.model.CovidCaseEntityMapper
import com.example.clase13.navigation.Navigator
import com.example.clase13.repository.CovidCaseRepository
import com.example.clase13.ui.covidCase.CovidCasesViewModel
import com.example.clase13.ui.covidCaseDetail.CovidCaseDetailViewModel
import com.example.clase13.ui.favorite.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single{ Room.databaseBuilder(get(),AppDatabase::class.java, "covid-case").build()}
    single { get<AppDatabase>().covidCaseDao() }
    single{ CovidCaseEntityMapper() }
    single { CovidCaseRepository(get(),get(),get()) }
    single{ Navigator()}
    viewModel { CovidCasesViewModel(get(),get()) }
    viewModel { CovidCaseDetailViewModel(get(),get()) }
    viewModel { FavoritesViewModel(get(),get()) }
}