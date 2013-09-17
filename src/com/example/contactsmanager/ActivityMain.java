package com.example.contactsmanager;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ActivityMain extends Activity {
	Context context = this;
	StableArrayAdapter adapter;
	


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        final ListView listview = (ListView) findViewById(R.id.listview);
        
        ArrayList<Contact> values = getStringContacts();

//        final List<Contact> contactList = new ArrayList<Contact>();
//        for (int i = 0; i < values.size(); i=i+1) {
//          contactList.add(values.get(i));
//        }

        
         adapter = new StableArrayAdapter(this, values);
            listview.setAdapter(adapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

              @Override
              public void onItemClick(AdapterView<?> parent, final View view,
                  int position, long id) {
            	  //returns name of contact pressed
                final Contact item = (Contact) parent.getItemAtPosition(position);
               openView(item);
                  
              }

            });
            
          }
        
  
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_add_contact:
                openAdd();
                return true;
            case R.id.action_sort:
                //create dialogue
            	
            	
            	    AlertDialog.Builder builder = new AlertDialog.Builder(context);
            	    builder.setTitle(R.string.sort_type);
            	    builder.setItems(R.array.sort_by_array, new DialogInterface.OnClickListener() {
            	               public void onClick(DialogInterface dialog, int which) {
            	               //not sure if this will do it, need to get it to call
            	            	  //get contacts from contact list again
            	            	   adapter.notifyDataSetChanged();
            	            	   
            	           }
            	    });
            	    AlertDialog dialog = builder.create();
            	    dialog.show();
            	
            	
            	
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    private void openAdd(){
    	Intent startNewActivityOpen = new Intent(ActivityMain.this, ActivityAddContact.class);
    	startActivityForResult(startNewActivityOpen, 0);
    }
    
    private void openView(Contact item){
    	Intent startNewActivityOpen = new Intent(ActivityMain.this, ContactView.class);
    	startNewActivityOpen.putExtra("name",item.getFullName());
    	startActivityForResult(startNewActivityOpen, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    

    
    private ArrayList<Contact> getStringContacts(){
    	ContactList cList = ContactList.getInstance();
    	ArrayList<Contact> values = cList.getList();
   
		return values;
    	
    }
}



