package com.example.clase12.covidCases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.clase12.R
import com.example.clase12.service.LocationService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng

class CovidCaseDetailFragment : Fragment() {

    private val viewModel: CovidCaseDetailViewModel by activityViewModels()
    private val args: CovidCaseDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_covid_case_detail, container, false)
        val country = args.country
        val map  = view.findViewById<MapView>(R.id.mapView)
        viewModel.loadCases(country)
        viewModel.myCase.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.titleLabel).text =
                    "Covid Case Detail from ${it.country}"
            view.findViewById<TextView>(R.id.deathLabel).text = "Deaths:  ${it.deaths}"
            view.findViewById<TextView>(R.id.positiveLabel).text = "Active:  ${it.active}"
            view.findViewById<TextView>(R.id.negativeLabel).text = "Recovered:  ${it.recovered}"
        })
        LocationService.getLocation().observe(viewLifecycleOwner,{
            map.getMapAsync{ map ->
                val location = LatLng(it.latitude,it.longitude)
                map.moveCamera(CameraUpdateFactory.newLatLng(location))
            }
        })

//        val favoriteButton  = view.findViewById<Button>(R.id.save_favorite_button)
//        favoriteButton.setOnClickListener {
////            if (viewModel.selectedCase.value != null ){
////                viewModel.addCase(viewModel.selectedCase.value !!)
////            }
//        }
//        val closeButton = view.findViewById<Button>(R.id.close_button)
//        closeButton.setOnClickListener {
//            viewModel.navigator.navigateUp()
//        }
        return view
    }


}