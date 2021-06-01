package com.example.clase13.networking

import com.example.clase13.model.CovidCaseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidCaseRepository {

    @GET("countries?yesterday")
    fun getCountriesCovidCases(): Call<List<CovidCaseModel>>

    @GET("countries/{country}")
    fun getCountry(@Path("country") country: String): Call<CovidCaseModel>
}