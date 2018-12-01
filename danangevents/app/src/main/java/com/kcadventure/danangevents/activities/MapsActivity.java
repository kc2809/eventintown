package com.kcadventure.danangevents.activities;

import static com.kcadventure.danangevents.util.Constants.EVENT_DATA;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kcadventure.danangevents.R;
import com.kcadventure.danangevents.models.Event;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

  private GoogleMap mMap;
  Event event;

  public static Intent getMapsActivityIntent(Context context, Event event){
    Intent intent = new Intent(context, MapsActivity.class);
    intent.putExtra(EVENT_DATA, event);
    return intent;
  }

  private void getData() {
    Intent intent = getIntent();
    event = (Event) intent.getSerializableExtra(EVENT_DATA);
    System.out.println(event);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps);
    getData();
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                                                              .findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
  }


  /**
   * Manipulates the map once available. This callback is triggered when the map is ready to be
   * used. This is where we can add markers or lines, add listeners or move the camera. In this
   * case, we just add a marker near Sydney, Australia. If Google Play services is not installed on
   * the device, the user will be prompted to install it inside the SupportMapFragment. This method
   * will only be triggered once the user has installed Google Play services and returned to the
   * app.
   */
  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;

    // Add a marker in Sydney and move the camera
    LatLng choThanhKhe = new LatLng(event.getLat(), event.getLon());
    mMap.addMarker(new MarkerOptions().position(choThanhKhe).title(event.getEvent_name()));
    mMap.moveCamera(CameraUpdateFactory.newLatLng(choThanhKhe));
    mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );

  }
}
