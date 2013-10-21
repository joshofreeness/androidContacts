package com.example.contactsmanager;

import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class ContactView extends Activity{
	//contact currently viewing
	private String name;
	Context cntxt;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = getIntent();
        setContentView(R.layout.contact_view_1);
        //get name of contact to be viewed
        name = myIntent.getStringExtra("name");
        cntxt = getApplicationContext();
        
    	String[] details = getDetails();
    	//setup all text views
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
        TextView dOB = (TextView)findViewById(R.id.contact_date_of_birth);
        dOB.setText(details[7]);
        ImageView image = (ImageView)findViewById(R.id.contact_image);
       //gets the image manager and then image
        ImageManager manager = ImageManager.getInstance();
        Bitmap bit = manager.getImage(details[8], 120);
        Drawable draw = new BitmapDrawable(getResources(),  bit);
    	//image.setBackground(draw);
    	image.setImageDrawable(draw);
    	//image.setImageResource(android.R.color.transparent);        
        
        //the following allows the user to make calls, texts and emails form within the app
        
        //NOTE:----------THIS MAY NOT WORK ON AN EMULATOR-------------
        ImageButton callMob = (ImageButton)findViewById(R.id.call_mobile_image);
        callMob.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		callNumber(1);
        	}
        	});
        ImageButton callWork = (ImageButton)findViewById(R.id.call_work_image);
        callWork.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		callNumber(3);
        	}
        	});
        ImageButton callHome = (ImageButton)findViewById(R.id.call_home_image);
        callHome.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		callNumber(2);
        	}
        	});
        ImageButton textMob = (ImageButton)findViewById(R.id.text_mobile_image);
        textMob.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		//Intent to text mobile
        		String[] details = getDetails();
        		Uri smsUri = Uri.parse("sms:"+ details[4]);
        		Intent intent = new Intent(Intent.ACTION_VIEW, smsUri);
        		startActivity(intent);
        		
        	}
        	});
        
        ImageButton sendEmail = (ImageButton)findViewById(R.id.email_image);
        sendEmail.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		//Intent to text mobile
        		String[] details = getDetails();
        		Uri emailUri = Uri.parse("mailto:"+ details[5]);
        		Intent intent = new Intent(Intent.ACTION_VIEW, emailUri);
        		startActivity(intent);
        		
        	}
        	});
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
                    	ContactList c = ContactList.getInstance();
                    	c.deleteContact(getContact(), cntxt);
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
                //starts edit contact passing the contact to be edited
            	Intent startNewActivityOpen = new Intent(ContactView.this, ContactEdit.class);
            	startNewActivityOpen.putExtra("name", name);
            	startActivityForResult(startNewActivityOpen, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    private void callNumber(int number){
    	//call the phone number depending on which button was pressed
    	String[]details = getDetails();
     	String phNo;
     	
     	if (number == 1){
     		phNo=details[4];
     	}else if(number == 2){
     		phNo=details[2];
     	}else{
     		phNo=details[3];
     	}
     	
     	Intent intent = new Intent(Intent.ACTION_DIAL);
     	intent.setData(Uri.parse("tel:" + phNo )); 
     	startActivity(intent); 
    	
    }
    
    public String[] getDetails(){
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
      	return contact.getDetails();
    	
    }
	
    public Contact getContact(){
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
     	return contact;
   	
   }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 1) {
        	finish();
            
        }
        
    }
	
}
