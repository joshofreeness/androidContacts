package com.example.contactsmanager;


import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

public class StableArrayAdapter extends ArrayAdapter<String> {
	  private final Context context;
	  private final ArrayList<String> names;
	  private final ArrayList<String> nums;

	  public StableArrayAdapter(Context context, ArrayList<String> name, ArrayList<String> num) {
	    super(context, R.layout.row_layout, name);
	    this.context = context;
	    this.names = name;
	    this.nums=num;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.row_layout, parent, false);
	    TextView textView = (TextView) rowView.findViewById(R.id.label);
	    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
	    TextView numbers = (TextView) rowView.findViewById(R.id.secondLine);
	    textView.setText(names.get(position));
	    numbers.setText(nums.get(position));
	    // Change the icon for Windows and iPhone
	    String s = names.get(position);
	    imageView.setImageResource(R.drawable.ic_launcher);

	    return rowView;
	  }
	}
