package com.example.clase13.ui.covidCase

import android.location.Geocoder
import android.os.Bundle
import android.view.*
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clase13.*
import com.example.clase13.model.CovidCaseModel
import com.example.clase13.service.LocationService
import com.example.clase13.ui.MainActivity
import org.koin.android.ext.android.inject
import java.util.*

class CovidCasesFragment : Fragment(), OnClickListener {


    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CovidRecyclerViewAdapter
    private val viewModel: CovidCasesViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorites, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_covid_casses, container, false)
        viewModel.bindView()
        adapter = CovidRecyclerViewAdapter(this)
        recyclerView = view.findViewById<RecyclerView>(R.id.covid_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        viewModel.cases.observe(viewLifecycleOwner, Observer {
            adapter.set(it)
        })
        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorites -> viewModel.navigator.navigateToFavorites()
            R.id.location -> getLocation()
        }
        return super.onOptionsItemSelected(item)
    }

    fun getLocation() {
        LocationService.getLocation().observe(this, {
            val geoCoder = Geocoder(this.context, Locale.getDefault())
            val address = geoCoder.getFromLocation(it.latitude, it.longitude, 1)
            val country = address.first().countryName
            if (country != null) {
                viewModel.loadCaseFromLocation(country)
            }
        })
    }

    override fun onClickItem(item: Any) {
        if (item is CovidCaseModel) {
            viewModel.selectCase(item)
            viewModel.navigator.navigateToDetail(item.country)
        }
    }

}