package com.kcadventure.danangevents.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kcadventure.danangevents.R;
import com.kcadventure.danangevents.models.Event;
import java.util.ArrayList;
import java.util.List;

public class MapViewFragment extends Fragment {

  MapView mMapView;
  private GoogleMap googleMap;
  private List<Event> events = new ArrayList<>();
  private DatabaseReference mDatabase;

  private void initDb() {
    mDatabase = FirebaseDatabase.getInstance().getReference();
    mDatabase.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        Log.e(TAG, dataSnapshot.child("event").);

        addDataChange(dataSnapshot.child("event").getChildren());
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  private void addDataChange(Iterable<DataSnapshot> dataChanges) {
    for (DataSnapshot dataSnapshot : dataChanges) {
      Event event = dataSnapshot.getValue(Event.class);
      events.add(event);
    }
    Log.e("SIZE event", events.size() + "");
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.location_fragment, container, false);
    initDb();

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
        events.stream().forEach(e -> addMarker(e));
        if (events.size() != 0) {
          LatLng choThanhKhe = new LatLng(events.get(0).getLat(), events.get(0).getLon());
          CameraPosition cameraPosition = new CameraPosition.Builder().target(choThanhKhe)
                                              .zoom(13.0f)
                                              .build();
          googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
      }
    });

    return rootView;
  }

  private void addMarker(Event event) {
    LatLng choThanhKhe = new LatLng(event.getLat(), event.getLon());
    googleMap.addMarker(new MarkerOptions().position(choThanhKhe).title(event.getEvent_name()))
        .showInfoWindow();
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
