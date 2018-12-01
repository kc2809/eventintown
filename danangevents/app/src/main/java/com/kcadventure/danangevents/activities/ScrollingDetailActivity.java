package com.kcadventure.danangevents.activities;

import static com.kcadventure.danangevents.util.Constants.EVENT_DATA;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.kcadventure.danangevents.R;
import com.kcadventure.danangevents.models.Event;

public class ScrollingDetailActivity extends AppCompatActivity {

  Event event;
  TextView tvTitle, tvDescription, tvLocation, tvAntended;

  public static Intent getScrollingDetailActivityIntent(Context context, Event event){
    Intent intent = new Intent(context, ScrollingDetailActivity.class);
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
    setContentView(R.layout.activity_scrolling_detail);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });

    initView();
    getData();
    setData();
  }

  private void initView() {
    tvTitle = findViewById(R.id.text_title);
    tvAntended = findViewById(R.id.text_antended);
    tvLocation = findViewById(R.id.text_location);
    tvDescription = findViewById(R.id.text_description);
  }

  private void setData() {
    tvTitle.setText(event.getEvent_name());
    tvAntended.setText(event.getAttending_count() + "");
    tvLocation.setText(event.getLocation());
    tvDescription.setText(event.getDescription());
  }
}
