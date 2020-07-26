package com.example.womensecapp;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpPolice extends FragmentActivity implements OnMapReadyCallback, LocationListener {
    EditText et1, et2, et3, et4, et5, et6;
    Button bt;
    LatLng lng;
    private GoogleMap mMap;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_police);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        et1 = findViewById(R.id.up_et1);
        et2 = findViewById(R.id.up_et2);
        et3 = findViewById(R.id.up_et3);
        et4 = findViewById(R.id.up_et4);
        et5 = findViewById(R.id.up_et5);
        et6 = findViewById(R.id.up_et6);
        bt = findViewById(R.id.up_bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog=new ProgressDialog(SignUpPolice.this);
                dialog.setTitle("wait");
                dialog.setMessage("Your data is being saved");
                dialog.show();
                // Create a new user with a first and last name
                Map<String, Object> station = new HashMap<>();
                station.put("name", et1.getText().toString());
                station.put("address", et2.getText().toString());
                station.put("pno", et3.getText().toString());
                station.put("sho", et4.getText().toString());
                station.put("latitude", et5.getText().toString());
                station.put("longitude", et6.getText().toString());

// Add a new document with a generated ID
                db.collection("stations")
                        .add(station)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(SignUpPolice.this, "Your data is saved", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                                SignUpPolice.this.finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUpPolice.this, "error", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        ActivityCompat.requestPermissions(SignUpPolice.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 10, this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(20.5937, 78.9629);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in India"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=this.getMenuInflater();
        mi.inflate(R.menu.mainmenu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.menu_up_1){
            if(lng==null)
                Toast.makeText(this, "Wait for GPS response", Toast.LENGTH_SHORT).show();
            else{
                CameraUpdate cu=CameraUpdateFactory.newLatLngZoom(lng,15);
                mMap.animateCamera(cu);
                mMap.clear();
                MarkerOptions mo=new MarkerOptions();
                mo.position(lng);
                Geocoder gc=new Geocoder(SignUpPolice.this);
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
        et5.setText((int) location.getLatitude());
        et6.setText((int) location.getLongitude());
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
