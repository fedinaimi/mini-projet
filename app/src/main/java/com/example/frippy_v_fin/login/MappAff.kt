package com.example.frippy_v_fin.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.frippy_v_fin.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MappAff : AppCompatActivity(), OnMapReadyCallback{

    private lateinit var mMap:GoogleMap
    val majusstore = LatLng(36.887520,10.166865)
    val fedistore = LatLng(36.8962769,10.1770798)
    val emnashop = LatLng(36.9017903,10.1863647)
    val achrefshop = LatLng(36.898666,10.181948)
    val espritshop = LatLng(36.8951395,10.1956126)


private var locationArrayList:ArrayList<LatLng>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapp_aff)

        val map = supportFragmentManager.findFragmentById(R.id.myMap) as SupportMapFragment
map.getMapAsync(this)
        locationArrayList = ArrayList()
        locationArrayList!!.add(majusstore)
        locationArrayList!!.add(fedistore)
        locationArrayList!!.add(emnashop)
        locationArrayList!!.add(achrefshop )
        locationArrayList!!.add(espritshop)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap =googleMap
        for (i in locationArrayList!!.indices){
            mMap.addMarker(MarkerOptions().position(locationArrayList!![i]).title("Fryppi"))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList!!.get(i)))
        }


    }
}