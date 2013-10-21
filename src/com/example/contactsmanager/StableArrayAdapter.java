package com.example.contactsmanager;


import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class StableArrayAdapter extends ArrayAdapter<Contact> {
	//save context and list of contacts
	  private final Context context;
	  private ArrayList<Contact> contacts;



	  public StableArrayAdapter(Context context, ArrayList<Contact> list ) {
	    super(context, R.layout.row_layout, list);
	    this.context = context;
	    this.contacts = list;

	  }

	  @SuppressLint("NewApi")
	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		  //set to be a custom layout
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.row_layout, parent, false);
	    //set values for each list entry
	    TextView textView = (TextView) rowView.findViewById(R.id.label);
	    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
	    TextView numbers = (TextView) rowView.findViewById(R.id.secondLine);
	    textView.setText((contacts.get(position)).getFullName());
	    numbers.setText((contacts.get(position)).getmNumber());
	    
	    String s = contacts.get(position).getImage();
	    BitmapWorkerOpen task = new BitmapWorkerOpen(imageView);
	    task.execute(s);
//	    ImageManager manager = ImageManager.getInstance();
//	    Bitmap image = manager.getImage(s, 60);
//	    imageView.setImageResource(R.drawable.ic_launcher);
//	    Drawable draw = new BitmapDrawable(context.getResources(),  image);
//    	imageView.setBackground(draw);
//    	imageView.setImageResource(android.R.color.transparent);  

	    return rowView;
	  }
	}
