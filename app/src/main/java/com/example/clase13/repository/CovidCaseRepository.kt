package com.example.clase13.repository

import androidx.lifecycle.MutableLiveData
import com.example.clase13.model.CovidCaseEntityMapper
import com.example.clase13.model.CovidCaseModel
import com.example.clase13.model.CovidCasesDao
import com.example.clase13.networking.CovidCaseRemoteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CovidCaseRepository(
    val covidCasesDao: CovidCasesDao,
    val remoteApi: CovidCaseRemoteRepository,
    val entityMapper: CovidCaseEntityMapper
) {

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    var cases = MutableLiveData<List<CovidCaseModel>>()
    var locationCountry = MutableLiveData<CovidCaseModel>()
    var selectedCase = MutableLiveData<CovidCaseModel>()


    init {
        loadLocalCases()
        loadRemoteCases()
    }

    fun loadCaseFromLocation(country: String) {
        locationCountry.value = entityMapper.mapFromCached(covidCasesDao.getCountry(country))
    }

    fun loadRemoteCases() {
        remoteApi.getCountriesCovidCases()
            .enqueue(object : Callback<List<CovidCaseModel>> {
                override fun onResponse(
                    call: Call<List<CovidCaseModel>>,
                    response: Response<List<CovidCaseModel>>
                ) {
                    val body = response.body()
                    if (body != null) {
                        //En la clases se vio AsyncTask pero esta deprecado desde API 30 esta es una nueva forma de hacerlo.
                        executor.execute {
                            body.forEach {
                                covidCasesDao.insertCase(entityMapper.mapToCached(it))
                            }
                            loadLocalCases()
                        }
                    }
                }

                override fun onFailure(call: Call<List<CovidCaseModel>>, t: Throwable) {
                    println(t.message)
                }

            })
    }

    fun loadLocalCases() {
        executor.execute {
            cases.postValue(covidCasesDao.getAllCases().map {
                entityMapper.mapFromCached(it)
            })
        }
    }

}