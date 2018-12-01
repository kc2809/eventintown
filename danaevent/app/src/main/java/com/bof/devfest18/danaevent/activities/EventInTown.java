package com.bof.devfest18.danaevent.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bof.devfest18.danaevent.R;
import com.bof.devfest18.danaevent.adapters.TabAdapter;
import com.bof.devfest18.danaevent.fragments.EventInTownFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventInTown extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter tabAdapter;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        List<Fragment> tabs = new ArrayList<>();
        tabs.add(new EventInTownFragment());
        tabs.add(new EventInTownFragment());
        tabs.add(new EventInTownFragment());
        tabAdapter = new TabAdapter(getSupportFragmentManager(), tabs, Arrays.asList("Event in Town", "My Event", "Map"));
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
}
