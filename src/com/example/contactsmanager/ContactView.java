package com.example.contactsmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactView extends Activity{
	private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = getIntent();
        setContentView(R.layout.contact_view);
        name = myIntent.getStringExtra("name");
        ContactList cList = ContactList.getInstance();
    	ArrayList<Contact> values = cList.getList();
    	//TODO: CAN LEAD TO NULL POINTER
    	Contact contact = null;
    	for (int i=0; i<values.size(); i++){
    		if (values.get(i).getFullName().equals(name)){
    			contact = values.get(i);
    		}
    	}
    	//Set fields
    	String[] details = contact.getDetails();
    	
        TextView firstName = (TextView)findViewById(R.id.contact_first_name);
        firstName.setText(details[0]);
        TextView lastName = (TextView)findViewById(R.id.contact_last_name);
        lastName.setText(details[1]);
        TextView hNum = (TextView)findViewById(R.id.contact_home_number);
        hNum.setText(details[2]);
        TextView wNum = (TextView)findViewById(R.id.contact_work_number);
        wNum.setText(details[3]);
        TextView mNum = (TextView)findViewById(R.id.contact_mobile_number);
        mNum.setText(details[4]);
        TextView email = (TextView)findViewById(R.id.contact_email_address);
        email.setText(details[5]);
        TextView home = (TextView)findViewById(R.id.contact_home_address);
        home.setText(details[6]);
        ImageView image = (ImageView)findViewById(R.id.contact_image);
        //TODO:Setup DOB details[7]
        //TODO: change when setup images details[8]
        image.setImageResource(R.drawable.ic_launcher);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contact_view, menu);
        return true;
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_delete_contact:
                
            	new AlertDialog.Builder(this)
                .setTitle("Delete contact")
                .setMessage("Are you sure you want to delete this contact?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { 
                    	//TODO: delete contact
                    	finish();
                    }
                 })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { 
                        // do nothing
                    }
                 })
                 .show();
            	
                return true;
            case R.id.action_edit_contact:
                //TODO: add what to do when edit
            	//must send details about current contact
            	//to partially fill new contact or something similar
            	Intent startNewActivityOpen = new Intent(ContactView.this, ContactEdit.class);
            	startNewActivityOpen.putExtra("name", name);
            	startActivityForResult(startNewActivityOpen, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	
	
}
