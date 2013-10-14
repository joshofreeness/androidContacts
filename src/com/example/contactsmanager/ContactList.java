package com.example.contactsmanager;

import java.io.File;
import java.io.FileOutputStream;
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
		   //TODO:Query a database
		   //will eventually query database
		  
		   SQLite db = new SQLite(context, null,null,1);
		   list.clear();
		   list.addAll(db.getAllContacts());
		   
//		   list.add(new Contact("sally","Suzan", "26459845","5645858","24652483","you@place.com","45 road","22/3/195","image.jpg"));
//		   list.add(new Contact("Bob","richard", "26459845","5645858","24652483","you@place.com","45 road","22/3/195","image.jpg"));
//		   list.add(new Contact("anne","McCaffery", "26459845","5645858","24652483","you@place.com","45 road","22/3/195","image.jpg"));
//		   list.add(new Contact("The","Doctor", "1","1","1","the@doctor.time","tardis","22/3/-5","image.jpg"));
//		   list.add(new Contact("sally","Blogs", "26459845","5645858","24652483","you@place.com","45 road","22/3/195","image.jpg"));
//		   list.add(new Contact("Richard","Fran", "26459845","5645858","24652483","you@place.com","45 road","22/3/195","image.jpg"));
//		   list.add(new Contact("Mum's","Uncle", "26459845","5645858","24652483","you@place.com","45 road","22/3/195","image.jpg"));
//		   list.add(new Contact("The","Master", "2","2","2","the@master.time","somewhere","22/3/-20","image.jpg"));
//		   list.add(new Contact("That","Blogs", "264","558","2463","you@place.com","45 road","22/3/195","image.jpg"));
//		   list.add(new Contact("Albert","Longname", "245","564858","2483","you@place.com","13 road","22/3/195","image.jpg"));
//		   list.add(new Contact("Pat","Postman", "21235","5567","78385","balck@cat.com","1 road","22/5/195","image.jpg"));
	   }
	   
	   public boolean saveContact(Contact c, Context context){
		   
		   SQLite db = new SQLite(context, null,null,1);
		   db.addContact(c);
		   return true;
  

	   }
	   
	   public boolean deleteContact(Contact c, Context context){
		   
		   SQLite db = new SQLite(context, null,null,1);
		   db.deleteContact(c);
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
