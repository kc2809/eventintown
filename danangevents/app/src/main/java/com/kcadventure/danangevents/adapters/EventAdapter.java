package com.kcadventure.danangevents.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.kcadventure.danangevents.R;
import com.kcadventure.danangevents.activities.ScrollingDetailActivity;
import com.kcadventure.danangevents.models.Event;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
  private List<Event> events;
  private Context context;

  public class MyViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

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
      v.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      int position = getAdapterPosition();
      Event event =  getEvents().get(position);
      Intent intent = ScrollingDetailActivity.getScrollingDetailActivityIntent(context, event);
      context.startActivity(intent);
    }
  }

  public List<Event> getEvents() {
    return events;
  }

  public EventAdapter(List<Event> events, Context context) {
    this.events = events;
    this.context = context;
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
