package com.jovandy.testrs.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import com.jovandy.testrs.R
import com.jovandy.testrs.api.respone.listdata.DataItem
import com.jovandy.testrs.ui.editor.EditorActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() , MainView{
    private lateinit var mainPresenter: MainPresenter
    var lat:String =""
    var longt:String = ""
    private var locationManager : LocationManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?
        mainPresenter = MainPresenter(this, this)
        getTimeFromAndroid()
//        logout.setOnClickListener(){
//            startActivity(Intent(this, SignInActivity::class.java))
//            finish()
//        }
        mainPresenter.doGetList()
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)

        fab.setOnClickListener(){
            val intent = Intent(this@MainActivity, EditorActivity::class.java)
            intent.putExtra("lay", "2")
            intent.putExtra("lat", lat)
            intent.putExtra("longt", longt)
            Log.e("cek put",lat )
            startActivity(intent)
        }
        swipe_refresh.setOnRefreshListener {
            mainPresenter.doGetList()

            // refresh your list contents somehow
            swipe_refresh.isRefreshing = false // reset the SwipeRefreshLayout (stop the loading spinner)
        }
    }
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            lat =location?.latitude.toString()
            longt = location?.longitude.toString()

        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    private fun getTimeFromAndroid() {
        val c = Calendar.getInstance()
        val timeOfDay = c[Calendar.HOUR_OF_DAY]
        if (timeOfDay >= 0 && timeOfDay < 12) {
            welcome.setText("Selamat Pagi!")
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            welcome.setText("Selamat Siang!")
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            welcome.setText("Selamat Sore!")
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            welcome.setText("Selamat Malam!")
        }
    }

    override fun onSetProgressBarVisibility(visibility: Int) {
        progress_bar.visibility = visibility
    }

    override fun showSukses(dataItem: List<DataItem?>) {
        val layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        recycler_view.layoutManager = layoutManager
        recycler_view.isNestedScrollingEnabled = true
        val mAdapter = MainAdapter(dataItem, this)
        recycler_view.adapter = mAdapter
        mAdapter.setOnItemClickListener(object : MainAdapter.ClickListener {
            override fun onItemClick(v: View, position: Int) {
                val intent = Intent(this@MainActivity, EditorActivity::class.java)
                intent.putExtra("lay", "1")
                intent.putExtra("id", dataItem?.get(position)?.id.toString())
                intent.putExtra("nama", dataItem?.get(position)?.title.toString())
                intent.putExtra("lat", dataItem?.get(position)?.lat.toString())
                intent.putExtra("longt", dataItem?.get(position)?.jsonMemberLong.toString())
                Log.e("cek put",dataItem?.get(position)?.id.toString() )
                startActivity(intent)

            }
        })
    }

    override fun showError(message: String) {
        TODO("Not yet implemented")
    }
}