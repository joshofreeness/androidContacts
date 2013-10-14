package com.example.contactsmanager;

import java.util.ArrayList;

import com.example.contactsmanager.ContactList.sortType;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ActivityMain extends Activity {
	//Get context variables for passing to methods/objects
	private Context context = this;
	//have a refrence to a single array adapter
	private StableArrayAdapter adapter;
	
	


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Get contacts List and refresh it
        ContactList cList = ContactList.getInstance();
        cList.refreshList(context);
        
        //Get the list view
        final ListView listview = (ListView) findViewById(R.id.listview);
        //get list of contacts
        ArrayList<Contact> values = getListContacts();


        //set up the adapter passing it values and set it to work on listview
         adapter = new StableArrayAdapter(this, values);
            listview.setAdapter(adapter);
            //set on click listener
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

              @Override
              public void onItemClick(AdapterView<?> parent, final View view,
                  int position, long id) {
            	  //returns name of contact pressed
                final Contact item = (Contact) parent.getItemAtPosition(position);
                //opens contact view and passes what was pressed (item)
               openView(item);            
              }
            });          
          }
        
  
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_add_contact:
            	//add new contact
                openAdd();
                return true;
            case R.id.action_sort:
                //create dialogue
            	    AlertDialog.Builder builder = new AlertDialog.Builder(context);
            	    builder.setTitle(R.string.sort_type);
            	    builder.setItems(R.array.sort_by_array, new DialogInterface.OnClickListener() {
            	               public void onClick(DialogInterface dialog, int which) {
            	            	  //get contacts from contact list again
            	            	   ContactList cList = ContactList.getInstance();
            	            	   //update the data according to what was selected
            	            	   if (which == 0){
            	            		   cList.setSortType(sortType.firstName);
            	            		   updatedData();
            	            	   }else if (which == 1){
            	            		   cList.setSortType(sortType.lastName);
               	            		   updatedData();
            	            		   
            	            	   }else{
            	            		   cList.setSortType(sortType.firstName);
            	            		   updatedData();
            	            	   }
            	           }
            	    });
            	    //show the dialog that was just built
            	    AlertDialog dialog = builder.create();
            	    dialog.show();
            	//default options for on menu option clicked
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    
    private void openAdd(){
    	//new intent to add contact
    	Intent startNewActivityOpen = new Intent(ActivityMain.this, ActivityAddContact.class);
    	startActivityForResult(startNewActivityOpen, 0);
    }
    
    private void openView(Contact item){
    	//new intent to view object selected
    	Intent startNewActivityOpen = new Intent(ActivityMain.this, ContactView.class);
    	startNewActivityOpen.putExtra("name",item.getFullName());
    	startActivityForResult(startNewActivityOpen, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar .
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    

    
    private ArrayList<Contact> getListContacts(){
    	//get the contact list from the contactList
    	ContactList cList = ContactList.getInstance();
    	ArrayList<Contact> values = cList.getList();
		return values;
    	
    }
    
    public void updatedData() {

        adapter.clear(); 
        ContactList cList = ContactList.getInstance();
        cList.refreshList(context);


        adapter.notifyDataSetChanged();

    }
    
    protected void onResume(){
    	super.onResume();
    	updatedData();
    }
}



