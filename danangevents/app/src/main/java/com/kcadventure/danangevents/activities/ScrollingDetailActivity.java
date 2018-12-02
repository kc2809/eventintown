package com.kcadventure.danangevents.activities;

import static com.kcadventure.danangevents.util.Constants.EVENT_DATA;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.kcadventure.danangevents.R;
import com.kcadventure.danangevents.models.Event;

public class ScrollingDetailActivity extends AppCompatActivity {

  Event event;
  TextView tvTitle, tvDescription, tvLocation, tvAntended;
  ImageButton btnMap;
  Button btnPayment;
  ImageView backDrop;

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
        sendEmail();
      }
    });

    initView();
    getData();
    setData();
  }

  private void sendEmail(){
    Intent send = new Intent(Intent.ACTION_SENDTO);
    String uriText = "mailto:" + Uri.encode("zhuukhanhz@gmail.com") +
                         "?subject=" + Uri.encode("Event in town") +
                         "&body=" + Uri.encode("Your payment is successfully");
    Uri uri = Uri.parse(uriText);

    send.setData(uri);
    startActivity(Intent.createChooser(send, "Send mail..."));
  }

  private void initView() {
    tvTitle = findViewById(R.id.text_title);
    tvAntended = findViewById(R.id.text_antended);
    tvLocation = findViewById(R.id.text_location);
    tvDescription = findViewById(R.id.text_description);
    btnMap = findViewById(R.id.btn_map);
    btnMap.setOnClickListener(v -> {
      startActivity(MapsActivity.getMapsActivityIntent(this, event));
    });

    btnPayment = findViewById(R.id.payment);
    btnPayment.setOnClickListener(v -> {
      payment();
    });

    backDrop = findViewById(R.id.backdrop);
  }

  private void setData() {
    tvTitle.setText(event.getEvent_name());
    tvAntended.setText(event.getAttending_count() + "");
    tvLocation.setText(event.getLocation());
    tvDescription.setText(event.getDescription());
    Glide.with(this)
        .load(event.getImage())
        .into(backDrop);
  }

  private void payment() {
// inflate the layout of the popup window
    LayoutInflater inflater = (LayoutInflater)
                                  getSystemService(LAYOUT_INFLATER_SERVICE);
    View popupView = inflater.inflate(R.layout.popup_window, null);
    ImageView imageView = popupView.findViewById(R.id.qrView);

    // create the popup window
    int width = LinearLayout.LayoutParams.WRAP_CONTENT;
    int height = LinearLayout.LayoutParams.WRAP_CONTENT;
    boolean focusable = true; // lets taps outside the popup also dismiss it
    final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

    // show the popup window
    // which view you pass in doesn't matter, it is only used for the window tolken
    popupWindow.showAtLocation(btnMap, Gravity.CENTER, 0, 0);

    // dismiss the popup window when touched
    popupView.setOnTouchListener((v, event) -> {
      imageView.setVisibility(View.GONE);
      imageView.setVisibility(View.INVISIBLE);
      (new Handler()).postDelayed(new Runnable() {
        @Override
        public void run() {
          imageView.setVisibility(View.VISIBLE);
        }
      }, 300);
      popupWindow.dismiss();
      return true;
    });
  }

  private void yourMethod() {
  }
}
