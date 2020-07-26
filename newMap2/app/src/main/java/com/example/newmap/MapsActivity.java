package com.example.newmap;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {
    EditText et;
    Button bt;
    TextView tv;
    private GoogleMap mMap;
    LatLng lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        et = findViewById(R.id.et1);
        bt = findViewById(R.id.bt);
        tv = findViewById(R.id.tv);
        ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(this, "Please wait....", Toast.LENGTH_SHORT).show();
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 10, this);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Geocoder gc=new Geocoder(MapsActivity.this);
                try {
                    List<Address> list=gc.getFromLocationName(et.getText().toString(),10);
                    mMap.clear();
                    String str="";
                    for(Address ad:list) {
                        LatLng lng = new LatLng(ad.getLatitude(), ad.getLongitude());
                        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(lng, 15);
                        mMap.animateCamera(cu);
                        MarkerOptions op = new MarkerOptions();
                        op.position(lng);
                        str+= ad.getAddressLine(0) + "----" + ad.getAddressLine(1) + "----" + ad.getPostalCode() + "----" + ad.getCountryName();
                        op.title(str);
                        mMap.addMarker(op);
                    }
                } catch (Exception e) {
                    Toast.makeText(MapsActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(28.3670, 79.4304);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Bareilly"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.m1)
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        else if(R.id.m4==item.getItemId()){
            if(lng==null)
                Toast.makeText(this, "Wait for GPS response", Toast.LENGTH_SHORT).show();
            else{
                CameraUpdate cu=CameraUpdateFactory.newLatLngZoom(lng,15);
                mMap.animateCamera(cu);
                mMap.clear();
                MarkerOptions mo=new MarkerOptions();
                mo.position(lng);
                Geocoder gc=new Geocoder(MapsActivity.this);
                try {
                    List<Address> addresses=gc.getFromLocation(lng.latitude,lng.longitude,1);
                    Address ad=addresses.get(0);
                    mo.title(ad.getAddressLine(0)+","+ad.getAddressLine(1));
                    mMap.addMarker(mo);
                } catch (Exception e) {
                    Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

        @Override
        public void onLocationChanged(Location location) {
        lng=new LatLng(location.getLatitude(),location.getLongitude());
            Toast.makeText(this, ""+lng.latitude+","+lng.longitude, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }
