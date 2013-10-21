package com.example.contactsmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import android.content.Context;

public class ContactList {
		//SINGLETON CLASS
	   private static ContactList INSTANCE;
	   
	   private ArrayList<Contact> list = new ArrayList<Contact>();
	   //set for appointing sort type
	   public enum sortType{
			firstName, lastName;
		}
		private sortType _sortBy = sortType.firstName;


	   //Singleton pattern
	   private ContactList() {}

	   public static ContactList getInstance() {
		   if (INSTANCE == null) {
			      INSTANCE = new ContactList();
		   }
			  return INSTANCE;
	 }
	   public void setSortType(sortType sortBy){
		   //what way will it be sorted when the getList method is called
		   _sortBy = sortBy;
	   }

	   
	   public void refreshList(Context context){
		  
		   SQLite db = new SQLite(context, null,null,1);
		   list.clear();
		   list.addAll(db.getAllContacts());
		   
	   }
	   
	   public boolean saveContact(Contact c, Context context){
		   
		   SQLite db = new SQLite(context, null,null,1);
		   return db.addContact(c);
		   
  

	   }
	   
	   public boolean deleteContact(Contact c, Context context){
		   //delete from database
		   SQLite db = new SQLite(context, null,null,1);
		   db.deleteContact(c);
		   //delete image
		   ImageManager manager = ImageManager.getInstance();
		   manager.deleteImage(c);
		   return true;
  

	   }

	   public ArrayList<Contact> getList(){
		   //gets list according to the enum that is currently set
		   if (_sortBy == sortType.firstName){
			   return sortListFirstName(list);
		   }else if (_sortBy == sortType.lastName){
			   return sortListLastName(list);
		   } else{
			   //if default or error(i.e. enum not set to value)
			   return sortListFirstName(list);
		   }
		   
	   }
	   
	   public void updateContact(Contact c, Context context){
		   SQLite db = new SQLite(context,null,null,1);
		   db.updateContact(c);
	   }
	   
	   private ArrayList<Contact> sortListFirstName(ArrayList<Contact> contacts) {
		   //sort by first name
		   Collections.sort(contacts, new Comparator<Contact>() {
		        @Override public int compare(Contact c1, Contact c2) {
		            return (c1.getFirstName()).compareToIgnoreCase(c2.getFirstName());
		        }

		    });

		   return contacts;
	   }
	   
	   private ArrayList<Contact> sortListLastName(ArrayList<Contact> contacts) {
		   //sort by last name
		   Collections.sort(contacts, new Comparator<Contact>() {
		        @Override public int compare(Contact c1, Contact c2) {
		            return (c1.getLastName()).compareToIgnoreCase(c2.getLastName());
		        }

		    });

		   return contacts;
	   }
	   

}
