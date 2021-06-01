package com.example.clase13.modules
import com.example.clase13.networking.CovidCaseApi
import com.example.clase13.networking.getOkClient
import com.example.clase13.networking.getRetrofit
import org.koin.dsl.module

val networkModule = module {
    single { getOkClient() }
    single { getRetrofit(get()) }
    single { CovidCaseApi(get()) }
}