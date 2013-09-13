package com.example.contactsmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ContactView extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = getIntent();
        setContentView(R.layout.contact_view);
        String name = myIntent.getStringExtra("name");
        TextView textView = (TextView)findViewById(R.id.contact_first_name);
        textView.setText(name);
        
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
                //TODO: edit contact
            	new AlertDialog.Builder(this)
                .setTitle("Delete contact")
                .setMessage("Are you sure you want to delete this contact?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { 
                        //delete contact
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	
	
}
