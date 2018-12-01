package com.kcadventure.danangevents.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.kcadventure.danangevents.R;
import com.kcadventure.danangevents.models.Event;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
  private List<Event> events;

  public static class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView eventTitle;
    public TextView eventPlace;
    public TextView eventTime;
    public ImageView eventBackground;
    public MyViewHolder(View v) {
      super(v);
      eventTitle = v.findViewById(R.id.event_title);
      eventPlace = v.findViewById(R.id.event_place);
      eventBackground = v.findViewById(R.id.event_background);
      eventTime = v.findViewById(R.id.time);
    }
  }

  public EventAdapter(List<Event> events) {
    this.events = events;
  }

  @Override
  public EventAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
      int viewType) {
    // create a new view
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.row_event, parent, false);
    return new MyViewHolder(view);
  }

  // Replace the contents of a view (invoked by the layout manager)
  @Override
  public void onBindViewHolder(MyViewHolder holder, int position) {
    // - get element from your dataset at this position
    // - replace the contents of the view with that element
    holder.eventTitle.setText(events.get(position).getEvent_name());
    holder.eventPlace.setText(events.get(position).getLocation());
    holder.eventTime.setText(events.get(position).getStart_time());

    // set background
    holder.eventBackground.setBackgroundResource(R.drawable.bg);


  }

  // Return the size of your dataset (invoked by the layout manager)
  @Override
  public int getItemCount() {
    return events.size();
  }
}
