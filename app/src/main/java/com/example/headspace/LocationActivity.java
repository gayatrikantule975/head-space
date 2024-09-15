package com.example.headspace;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.headspace.databinding.ActivityLocation2Binding;
import com.google.android.gms.maps.model.PolylineOptions;

public class LocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityLocation2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLocation2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng mountreachOffice = new LatLng(20.89903051174735, 77.76381686679524);

        mMap.addMarker(new MarkerOptions().position(mountreachOffice).title("Marker in Mountreach Solution pvt.ltd.")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.officemap)));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(mountreachOffice));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mountreachOffice, 16), 5000, null);
        mMap.addCircle(new CircleOptions()
                .strokeColor(Color.parseColor("#F8DF07"))
                .fillColor(Color.argb(70, 50, 150, 50)).
                radius(150).strokeWidth(5)
                .center(mountreachOffice));
        LatLng GPJ = new LatLng(19.86882186006452, 75.8256126802595);
        mMap.addMarker(new MarkerOptions().position(GPJ).title("Marker in Government Poly Jalna")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.collegemap)));
        mMap.addCircle(new CircleOptions().center(GPJ).radius(150).strokeColor(Color.parseColor("#F8DF07")).
                strokeWidth(5).fillColor(Color.argb(70,150,50,50)));
        mMap.addPolyline(new PolylineOptions()
                .add(mountreachOffice, GPJ)
                .color(Color.BLUE).width(5));

    }
}