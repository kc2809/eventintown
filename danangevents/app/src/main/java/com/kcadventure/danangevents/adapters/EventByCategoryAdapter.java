package com.kcadventure.danangevents.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.kcadventure.danangevents.R;
import com.kcadventure.danangevents.models.Category;
import com.kcadventure.danangevents.models.Event;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EventByCategoryAdapter extends
    RecyclerView.Adapter<EventByCategoryAdapter.MyViewHolder> {

  private List<Event> events;
  private List<Category> categories;
  private Context context;

  public EventByCategoryAdapter(List<Event> events, List<Category> categories,Context context) {
    this.events = events;
    this.categories = categories;
    this.context = context;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent,
      int viewType) {
    // create a new view
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.event_in_town_category_fragment, parent, false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(EventByCategoryAdapter.MyViewHolder holder, int position) {
    // - get element from your dataset at this position
    // - replace the contents of the view with that element
    List<Event> filteredEvent = events.stream().filter(event -> event.getCategory_id() == categories.get(position).getCategory_id()).collect(
        Collectors.toList());

    if (filteredEvent.size() != 0) {
      holder.category.setText(categories.get(position).getCategory_name());

      // set background
      holder.recyclerView.setAdapter(new EventAdapter(filteredEvent, context));
      holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
    } else {
      holder.recyclerView.setVisibility(View.GONE);
      holder.category.setVisibility(View.GONE);
    }


  }

  // Return the size of your dataset (invoked by the layout manager)
  @Override
  public int getItemCount() {
    return categories.size();
  }

  static class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView category;
    public RecyclerView recyclerView;

    public MyViewHolder(View v) {
      super(v);
      category = v.findViewById(R.id.category);
      recyclerView = v.findViewById(R.id.recyclerViewCategory);
    }
  }
}
