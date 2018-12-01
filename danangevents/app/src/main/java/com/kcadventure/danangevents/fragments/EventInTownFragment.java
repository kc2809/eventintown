package com.kcadventure.danangevents.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kcadventure.danangevents.R;
import com.kcadventure.danangevents.adapters.EventByCategoryAdapter;
import com.kcadventure.danangevents.models.Category;
import com.kcadventure.danangevents.models.Event;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventInTownFragment extends Fragment {
  private static String TAG = "EVENT IN TOWN FRAGMENT";
  RecyclerView recyclerView;
  private DatabaseReference mDatabase;
  List<Event> events = new ArrayList<>();
  List<Category> categories = new ArrayList<>();
  private EventByCategoryAdapter mAdapter;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.event_in_town_fragment, container, false);
    initDb();
    recyclerView = view.findViewById(R.id.recyclerView);
    mAdapter = new EventByCategoryAdapter(events, categories, getContext(), Glide.with(this));
    recyclerView.setAdapter(mAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    return view;
  }

  private void initDb() {
    mDatabase = FirebaseDatabase.getInstance().getReference();
    mDatabase.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        Log.e(TAG, dataSnapshot.child("event").);

        addDataChange(dataSnapshot.child("event").getChildren());
        addCategory(dataSnapshot.child("category").getChildren());
//        addDataChange(dataSnapshot.getChildren());
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }
  private void addDataChange(Iterable<DataSnapshot> dataChanges) {
    System.out.println(dataChanges);
    for(DataSnapshot dataSnapshot : dataChanges) {
      Event event = dataSnapshot.getValue(Event.class);
      events.add(event);
    }
    Log.e("SIZE event", events.size()+"");
    mAdapter.notifyDataSetChanged();
    events.stream().forEach( e -> Log.e(TAG, e.toString()));
  }

  private void addCategory(Iterable<DataSnapshot> dataChanges) {
    System.out.println(dataChanges);
    for(DataSnapshot dataSnapshot : dataChanges) {
      Category cat = dataSnapshot.getValue(Category.class);
      categories.add(cat);
    }

    Log.e("SIZE cat", categories.size()+"");

    mAdapter.notifyDataSetChanged();
  }
}
