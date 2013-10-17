package com.example.contactsmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


public class ContactEdit extends Activity{
	Context contextActivity=this;
	Context contextApp;
	//User can only select one image
	private static final int PICK_IMAGE = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = getIntent();
        setContentView(R.layout.contact_edit);
        contextApp = getApplicationContext();
        //extract what contact we are editing
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
        EditText dOB = (EditText)findViewById(R.id.contact_date_of_birth_edit);
        dOB.setText(details[7]);
       
        
        
        ImageButton image = (ImageButton)findViewById(R.id.contact_image_edit);
        //TODO: change when setup images details[8]
        image.setImageResource(R.drawable.ic_launcher);
        image.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		
        		//ask what you want to do, take picture or open saved image
        		AlertDialog.Builder builder = new AlertDialog.Builder(contextActivity);
        	    builder.setTitle(R.string.pick_image);
        	    builder.setItems(R.array.pick_image_array, new DialogInterface.OnClickListener() {
        	               public void onClick(DialogInterface dialog, int which) {
        	               //open gallery or camera
        	            	   if (which == 0){ //Gallery
        	            		   Intent intent = new Intent();
        	            		   intent.setType("image/*");
        	            		   intent.setAction(Intent.ACTION_GET_CONTENT);
        	            		   startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        	            		   //TODO: Do something with the selected image
        	            	   }else{ //camera
        	            		   
        	            		   Intent cameraIntent = new Intent(
        	            		            android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        	            		    startActivityForResult(cameraIntent, 1);
        	            		    //TODO: Store image in app (will also go to gallery)
        	            	   }
        	            		   
        	            	   
        	            	   
        	           }


        	    });
        	    //display setup dialog
        	    AlertDialog dialog = builder.create();
        	    dialog.show();
        	}});
    
        	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contact_edit, menu); 

        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()) {
        	case R.id.action_cancel_edit:
        		finish();
        		return true;
        	case R.id.action_save_contact_edit:
        		ContactList list = ContactList.getInstance();
        		getContact();
        		list.updateContact(getContact(), contextApp);
        		setResult(1, null);
        		finish();
        		return true;
        	default:
        		return super.onOptionsItemSelected(item);
    		
    	}
    }
    
    private Contact getContact(){
    	
    	EditText firstNameEdit = (EditText)findViewById(R.id.contact_first_name_edit);
        String firstName = firstNameEdit.getText().toString();
        EditText lastNameEdit = (EditText)findViewById(R.id.contact_last_name_edit);
        String lastName = lastNameEdit.getText().toString();
        EditText hNum = (EditText)findViewById(R.id.contact_home_number_edit);
        String home = hNum.getText().toString();
        EditText wNum = (EditText)findViewById(R.id.contact_work_number_edit);
        String work = wNum.getText().toString();
        EditText mNum = (EditText)findViewById(R.id.contact_mobile_number_edit);
        String mobile = mNum.getText().toString();
        EditText emailEdit = (EditText)findViewById(R.id.contact_email_address_edit);
        String email = emailEdit.getText().toString();
        EditText homeAdd = (EditText)findViewById(R.id.contact_home_address_edit);
        String homeAddress = homeAdd.getText().toString();
        EditText dOBEdit = (EditText)findViewById(R.id.contact_date_of_birth_edit);
        String dOB = dOBEdit.getText().toString();

    	
    	Contact contact = new Contact(firstName, lastName, home, work, mobile, email,
    			homeAddress, dOB, "b");
    	
    	
    	return contact;
    }

}
