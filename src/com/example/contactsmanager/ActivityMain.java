package com.example.contactsmanager;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ActivityMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final ListView listview = (ListView) findViewById(R.id.listview);
        
        String[] values = getStringContacts();

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
          list.add(values[i]);
        }
        //When changing to contacts objects change list to object list to send to
        //StableArrayAdapter
        final StableArrayAdapter adapter = new StableArrayAdapter(this, values);
            listview.setAdapter(adapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

              @Override
              public void onItemClick(AdapterView<?> parent, final View view,
                  int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
               openAdd();
                  
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
                //TODO: add what to do when sort
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    private void openAdd(){
    	Intent startNewActivityOpen = new Intent(ActivityMain.this, ActivityAddContact.class);
    	startActivityForResult(startNewActivityOpen, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private String[] getStringContacts(){
    	String[] values = new String[] { "Jo Blogs", "Richard Banks",
    			"Suzy Sue", "Lucy Luu", "Someone Awesome", "Joe blogs brother",
    			"Nancy Drew", "Hermione Granger", "Dr Who", "Philip Gong",
    			"Captain Kirk", "Spok", "JajaBinks", "Sally Anne", "That Guy",
    			"That Girl"
    	};
		return values;
    	
    }
}



