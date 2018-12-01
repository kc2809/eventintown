package com.kcadventure.danangevents.activities;

import android.provider.ContactsContract.Data;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kcadventure.danangevents.R;
import com.kcadventure.danangevents.adapters.TabAdapter;
import com.kcadventure.danangevents.fragments.EventInTownFragment;
import com.kcadventure.danangevents.fragments.MapViewFragment;
import com.kcadventure.danangevents.models.Event;
import com.kcadventure.danangevents.models.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener {

  private static final String TAG = "MAIN ACTIVITY";
  private Toolbar toolbar;
  private TabLayout tabLayout;
  private ViewPager viewPager;
  private TabAdapter tabAdapter;
  private DatabaseReference mDatabase;

  List<Event> events = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setupToolbar();
    initLayout();

    // Overview get data from firebase
    mDatabase = FirebaseDatabase.getInstance().getReference();
    mDatabase.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        Log.e(TAG, dataSnapshot.child("event").);

        Log.e(TAG, dataSnapshot.getValue().toString());
        Log.e(TAG, dataSnapshot.getChildrenCount()+"");
        Log.e(TAG, dataSnapshot.child("event").getValue().toString());

        addDataChange(dataSnapshot.child("event").getChildren());
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
      Log.e(TAG, "EVENTT: "  +  event.toString());
      Log.e(TAG, dataSnapshot.child("user").getValue().toString());
      Iterable<DataSnapshot> users = dataSnapshot.child("user").getChildren();
      for(DataSnapshot user: users){
        Log.e(TAG, user.getValue().toString());
//        User u = user.getValue(User.class);
//        Log.e(TAG,"USER: " +  u.toString());
      }
      events.add(event);
    }

    Log.e(TAG, "test: ");
    events.stream().forEach( e-> Log.e(TAG, e.toString()));
  }

  private void initLayout() {
    viewPager = findViewById(R.id.viewpager);
    tabLayout = findViewById(R.id.tab_layout);
    initTablayout();
  }

  private void initTablayout() {
    List<Fragment> tabs = new ArrayList<>();
    tabs.add(new EventInTownFragment());
    tabs.add(new EventInTownFragment());
    tabs.add(new MapViewFragment());

//    tabs.add(new EventInTownFragment());
    tabAdapter = new TabAdapter(getSupportFragmentManager(), tabs, Arrays.asList("Event in Town", "My Event", "Map"));
    viewPager.setAdapter(tabAdapter);
    tabLayout.setupWithViewPager(viewPager);
  }

  private void setupToolbar() {
    toolbar = findViewById(R.id.tb_top);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("");
    toolbar.setOnMenuItemClickListener(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    ((SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search)))
        .setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String query) {
            return false;
          }

          @Override
          public boolean onQueryTextChange(String newText) {
//            search(newText);
            Log.e(TAG, newText);
            return true;
          }
        });
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onMenuItemClick(MenuItem item) {
    if (item.getItemId() == R.id.action_change_style) {
//      changeStyle();
      Log.e(TAG, "change statle");
    }
    return false;
  }
}
