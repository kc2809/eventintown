package com.bof.devfest18.danaevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bof.devfest18.danaevent.adapters.EventAdapter;
import com.bof.devfest18.danaevent.models.Event;
import com.google.firebase.database.DatabaseReference;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fetch from firebase
        List<Event> events = Arrays.asList(new Event("1", "Google Devfest 2018", "2 Quang Trung", "1"),
                new Event("1", "Google Devfest 2018", "2 Quang Trung", "1"),
                new Event("1", "Google Devfest 2018", "2 Quang Trung", "1"),
                new Event("1", "Google Devfest 2018", "2 Quang Trung", "1"));
        recyclerView = findViewById(R.id.event_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new EventAdapter(events));
    }
}
