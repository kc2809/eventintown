package com.kcadventure.danangevents.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kcadventure.danangevents.R;
import com.kcadventure.danangevents.models.Category;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<Category> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private List<Category> categories;
    private int mResource;
    private final LayoutInflater mInflater;


    public SpinnerAdapter(Context context, int textViewResourceId,
                       List<Category> categories) {
        super(context, textViewResourceId, categories);
        this.context = context;
        this.categories = categories;
        mInflater = LayoutInflater.from(context);
        mResource = textViewResourceId;
    }

    @Override
    public int getCount(){
        return categories.size();
    }

    @Override
    public Category getItem(int position){
        return categories.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        View view = mInflater.inflate(mResource, parent, false);
        TextView label = view.findViewById(R.id.spinner_item);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(categories.get(position).getCategory_name());

        // And finally return your dynamic (or custom) view for each spinner item
        return view;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        View view = mInflater.inflate(mResource, parent, false);
        TextView label = view.findViewById(R.id.spinner_item);
        label.setText(categories.get(position).getCategory_name());

        return view;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
