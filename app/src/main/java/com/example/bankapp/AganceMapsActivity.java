package com.example.bankapp;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.bankapp.databinding.ActivityAganceMapsBinding;

import java.util.ArrayList;

public class AganceMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityAganceMapsBinding binding;
    TextView txt_phone;
    ArrayList<Marker> markers;
    Button btn_call;
    Uri telNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAganceMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        txt_phone=findViewById(R.id.txt_phone_num_map);
        btn_call=findViewById(R.id.btn_call);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        markers=new ArrayList<>();

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


        // Add a marker in Sydney and move the camera
        LatLng rabat = new LatLng(34.020882, -6.841650);
        mMap.addMarker(new MarkerOptions().position(rabat).title("Marker in Rabat").snippet("0612345678"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(rabat,5));

        addMarkersOnMap(googleMap);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull com.google.android.gms.maps.model.Marker marker) {
                txt_phone.setText(marker.getSnippet());
                telNumber=Uri.parse("tel:"+marker.getSnippet());
               // Toast.makeText(AganceMapsActivity.this, marker.getSnippet(), Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (telNumber==null){
                    Toast.makeText(AganceMapsActivity.this, R.string.Choose_a_marker, Toast.LENGTH_LONG).show();
                    return;
                }

                if (getPermission())
                {
                    Toast.makeText(AganceMapsActivity.this, "Please Allow Permission !!", Toast.LENGTH_LONG).show();
                }else{
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(telNumber);//change the number
                    startActivity(callIntent);
                }

            }
        });


    }


    public Boolean getPermission(){

        if (ActivityCompat.checkSelfPermission(AganceMapsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1);
            return true;
        }
        return false;

    }

    public void addMarkersOnMap(GoogleMap googleMap){
        markers.add(new Marker(34.05459963,-5.000377239,"Fes Boulemane","0612345678"));
        markers.add(new Marker(33.59997622,-7.616367433,"Grand Casablanca","0611111111"));
        markers.add(new Marker(34.02529909,-6.83613082,"Rabat Sale Zemmour Zaer","0622222222"));
        markers.add(new Marker(35.02038047,-5.909985801,"Tanger Tetouan","0633333333"));

        mMap = googleMap;

        for (int i = 0; i < markers.size(); i++) {

            LatLng rabat = new LatLng(markers.get(i).getLati(), markers.get(i).getLongt());
            mMap.addMarker(new MarkerOptions().position(rabat).title("Marker in "+markers.get(i).getAgence_name()).snippet(markers.get(i).getPhone_number()));
        }

    }
}