package com.example.clase13.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.clase13.R
import com.example.clase13.navigation.Navigator
import com.example.clase13.service.LocationService
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        navigator.activity = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        askPermissions()
        LocationService.startLocationService(this)
    }

    private fun askPermissions(){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),200)
        }
    }

}