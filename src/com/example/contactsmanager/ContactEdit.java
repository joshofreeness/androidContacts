package com.example.contactsmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;


public class ContactEdit extends Activity{
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = getIntent();
        setContentView(R.layout.contact_edit);
        String name = myIntent.getStringExtra("name");
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
    	
    	EditText firstName = (EditText)findViewById(R.id.contact_first_name_edit);
        firstName.setText(details[0]);
        EditText lastName = (EditText)findViewById(R.id.contact_last_name_edit);
        lastName.setText(details[1]);
        EditText hNum = (EditText)findViewById(R.id.contact_home_number_edit);
        hNum.setText(details[2]);
        EditText wNum = (EditText)findViewById(R.id.contact_work_number_edit);
        wNum.setText(details[3]);
        EditText mNum = (EditText)findViewById(R.id.contact_mobile_number_edit);
        mNum.setText(details[4]);
        EditText email = (EditText)findViewById(R.id.contact_email_address_edit);
        email.setText(details[5]);
        EditText home = (EditText)findViewById(R.id.contact_home_address_edit);
        home.setText(details[6]);
        ImageButton image = (ImageButton)findViewById(R.id.contact_image_edit);
        //TODO:Setup DOB details[7]
        //TODO: change when setup images details[8]
        image.setImageResource(R.drawable.ic_launcher);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contact_edit, menu); 
        
        //TODO: make buttons work
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()) {
        	case R.id.action_cancel_edit:
        		finish();
        		return true;
        	case R.id.action_save_contact_edit:
        		//save contact
        		finish();
        		return true;
        	default:
        		return super.onOptionsItemSelected(item);
    		
    	}
    }

}
