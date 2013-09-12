package com.example.contactsmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ContactView extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_view);
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
            	//must send details about current contact
            	//to partially fill new contact or something similar
                return true;
            case R.id.action_edit_contact:
                //TODO: add what to do when edit
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	
	
}
