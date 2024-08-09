package com.zhikunfan_comp304lab05_ex1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhikunfan_comp304lab05_ex1.adapter.LandmarkTypeAdapter
import com.zhikunfan_comp304lab05_ex1.model.Landmark
import com.zhikunfan_comp304lab05_ex1.model.LandmarkType

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LandmarkTypeAdapter
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private var toastShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view_landmark_types)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val landmarkTypes = getLandmarkTypes()
        adapter = LandmarkTypeAdapter(landmarkTypes) { item ->
            val intent = Intent(this, LandmarkListActivity::class.java)
            intent.putExtra("landmarkType", item.typeName)
            intent.putParcelableArrayListExtra("landmarks", ArrayList(item.landmarks))
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                if (!toastShown) {
                    Toast.makeText(this@MainActivity, "Location: ${location.latitude}, ${location.longitude}", Toast.LENGTH_LONG).show()
                    toastShown = true
                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 1)
            return
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
        Log.d("MainActivity", "Number of landmark types: ${landmarkTypes.size}")

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
            }
        } else {
            Toast.makeText(this, "Location permission is required", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getLandmarkTypes(): List<LandmarkType> {
        val attractions = listOf(
            Landmark("CN Tower", "301 Front St W, Toronto, ON", 43.6426, -79.3871),
            Landmark("Ripley's Aquarium of Canada", "288 Bremner Blvd, Toronto, ON", 43.6424, -79.3860),
            Landmark("Niagara Falls", "Niagara Falls, ON", 43.0962, -79.0377),
            Landmark("Casa Loma", "1 Austin Terrace, Toronto, ON", 43.6780, -79.4094),
            Landmark("Toronto Islands", "Toronto, ON", 43.6205, -79.3782)
        )

        val museums = listOf(
            Landmark("Royal Ontario Museum", "100 Queens Park, Toronto, ON", 43.6677, -79.3948),
            Landmark("Art Gallery of Ontario", "317 Dundas St W, Toronto, ON", 43.6536, -79.3925),
            Landmark("Hockey Hall of Fame", "30 Yonge St, Toronto, ON", 43.6469, -79.3772),
            Landmark("Bata Shoe Museum", "327 Bloor St W, Toronto, ON", 43.6677, -79.4001),
            Landmark("Aga Khan Museum", "77 Wynford Dr, Toronto, ON", 43.7258, -79.3291)
        )

        val parks = listOf(
            Landmark("High Park", "1873 Bloor St W, Toronto, ON", 43.6465, -79.4637),
            Landmark("Trinity Bellwoods Park", "790 Queen St W, Toronto, ON", 43.6473, -79.4174),
            Landmark("Toronto Botanical Garden", "777 Lawrence Ave E, Toronto, ON", 43.7336, -79.3634),
            Landmark("Rouge National Urban Park", "1749 Meadowvale Rd, Toronto, ON", 43.8250, -79.1764),
            Landmark("Edwards Gardens", "755 Lawrence Ave E, Toronto, ON", 43.7347, -79.3563)
        )

        return listOf(
            LandmarkType("Attractions", attractions),
            LandmarkType("Museums", museums),
            LandmarkType("Parks", parks)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        locationManager.removeUpdates(locationListener)
    }
}
