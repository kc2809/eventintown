package com.kcadventure.danangevents.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kcadventure.danangevents.R;

public class MapViewFragment extends Fragment {

  MapView mMapView;
  private GoogleMap googleMap;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.location_fragment, container, false);

    mMapView = rootView.findViewById(R.id.mapView);
    mMapView.onCreate(savedInstanceState);

    mMapView.onResume(); // needed to get the map to display immediately

    try {
      MapsInitializer.initialize(getActivity().getApplicationContext());
    } catch (Exception e) {
      e.printStackTrace();
    }

    mMapView.getMapAsync(new OnMapReadyCallback() {
      @Override
      public void onMapReady(GoogleMap mMap) {
        googleMap = mMap;

        // For showing a move to my location button
//        googleMap.setMyLocationEnabled(true);

        // For dropping a marker at a point on the Map
        LatLng sydney = new LatLng(16.061556, 108.182418);
        LatLng choThanhKhe = new LatLng(16.058662, 108.185249);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Cho Thanh Long"))
            .showInfoWindow();
        googleMap.addMarker(new MarkerOptions().position(choThanhKhe).title("cho thanh khe"))
            .showInfoWindow();
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        googleMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );

        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(choThanhKhe).zoom(17.0f)
                                            .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
      }
    });

    return rootView;
  }

  @Override
  public void onResume() {
    super.onResume();
    mMapView.onResume();
  }

  @Override
  public void onPause() {
    super.onPause();
    mMapView.onPause();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mMapView.onDestroy();
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    mMapView.onLowMemory();
  }
}
