package com.zhikunfan_comp304lab05_ex1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.zhikunfan_comp304lab05_ex1.model.Landmark

class LandmarkDetailActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var landmark: Landmark

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmark_detail)


        landmark = intent.getParcelableExtra("landmark")!!

        findViewById<TextView>(R.id.text_view_name).text = landmark.name
        findViewById<TextView>(R.id.text_view_address).text = landmark.address
        findViewById<TextView>(R.id.text_view_coordinates).text = "Latitude: ${landmark.latitude}, Longitude: ${landmark.longitude}"


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        val goBackButton = findViewById<Button>(R.id.button_go_back)
        goBackButton.setOnClickListener {
            finish(
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val location = LatLng(landmark.latitude, landmark.longitude)

        mMap.addMarker(MarkerOptions().position(location).title(landmark.name))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15F))

        mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
    }
}
