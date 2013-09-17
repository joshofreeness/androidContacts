package com.example.contactsmanager;

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


public class ActivityAddContact extends Activity{
	Context context;
	Context contextActivity=this;
	private static final int PICK_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        context = getApplicationContext();
        
        ImageButton image = (ImageButton)findViewById(R.id.contact_image);
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
        	    AlertDialog dialog = builder.create();
        	    dialog.show();
        		
        	}
        });

    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_cancel:
                finish();
                return true;
            case R.id.action_save_contact:
            	
            	EditText textLastName = (EditText)findViewById(R.id.contact_last_name);
            	String lastName = textLastName.getText().toString();
            	
            	EditText textFirstName = (EditText)findViewById(R.id.contact_first_name);
            	String firstName = textFirstName.getText().toString();
            	
            	EditText textMobile = (EditText)findViewById(R.id.contact_mobile_number);
            	String mobile = textMobile.getText().toString();
            	
            	EditText textHome = (EditText)findViewById(R.id.contact_home_number);
            	String home = textHome.getText().toString();
            	
            	EditText textWork = (EditText)findViewById(R.id.contact_work_number);
            	String work = textWork.getText().toString();
            	
            	EditText textEmail = (EditText)findViewById(R.id.contact_email_address);
            	String email = textEmail.getText().toString();
            	
            	EditText textHomeAddress = (EditText)findViewById(R.id.contact_home_address);
            	String homeAddress = textHomeAddress.getText().toString();
            	
            	Contact contact = new Contact(firstName, lastName, home, work, mobile, email,
            			homeAddress, "a", "b");
            	
            	ContactList list = ContactList.getInstance();
            	if (list.saveContact(contact, context)){
            		finish();            		
            	}else{
            		Intent startNewActivityOpen = new Intent(ActivityAddContact.this, AlreadyExists.class);
                	startActivityForResult(startNewActivityOpen, 0);
            	}
            	
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_add_contact, menu);
        return true;
    }

}
