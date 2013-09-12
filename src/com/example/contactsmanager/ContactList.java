package com.example.contactsmanager;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;



public class ContactList {
	   private static ContactList INSTANCE;


	   //Singleton pattern
	   private ContactList() {}

	   public static ContactList getInstance() {
		   if (INSTANCE == null) {
			      INSTANCE = new ContactList();
		   }
			  return INSTANCE;
	 }
	   
	   public boolean saveContact(Contact c, Context context){
		   
		   String[] values = c.getDetails();
		   FileOutputStream outputStream;
		   String fileName = values[0] + "_" + values[1] 
					+ ".contact";
			File file = new File(context.getFilesDir(),fileName);
			
			if (file.exists()){
				return false;
			}else{
				

				try {
				  outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
				  outputStream.write(values.toString().getBytes());
				  outputStream.close();
				} catch (Exception e) {
				  e.printStackTrace();
				}
				
				return true;
			}
			
		   

	   }
	   
	   public String[] getList(){
		   String[] values = new String[] { "Jo Blogs","2314568", "Richard Banks",
	    			"58497565","Suzy Sue","13254678", "Lucy Luu","0210254789", "Someone Awesome", "Joe blogs brother",
	    			"13584987","Nancy Drew","74152346","8521345", "Hermione Granger","123", "Dr Who","1","Philip Gong",
	    			"15478954","Captain Kirk","007", "Spok","7541325", "JajaBinks","666", "Sally Anne","45", "That Guy",
	    			"578945102","That Girl","587913248"};
		   
		   return values;
	   }
}
