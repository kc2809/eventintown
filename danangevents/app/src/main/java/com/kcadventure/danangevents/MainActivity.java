package com.kcadventure.danangevents;

import android.support.design.widget.TabLayout;
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

public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener {

  private static final String TAG = "MAIN ACTIVITY";
  private Toolbar mToolbar;
  private TabLayout mTabLayout;
  private ViewPager mViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setupToolbar();
  }

  private void setupToolbar() {
    mToolbar = findViewById(R.id.tb_top);
    setSupportActionBar(mToolbar);
    getSupportActionBar().setTitle("");
    mToolbar.setOnMenuItemClickListener(this);
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
