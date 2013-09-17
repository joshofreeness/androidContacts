package com.example.contactsmanager;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Context;



public class ContactList {
	
	   private static ContactList INSTANCE;
	   ArrayList<Contact> list = new ArrayList<Contact>();
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
		   _sortBy = sortBy;
	   }

	   
	   public void refreshList(){
		   //will eventually query database
		   list.clear();
		   list.add(new Contact("Bob","Suzan", "26459845","5645858","24652483","you@place.com","45 road","22/3/195","image.jpg"));
		   list.add(new Contact("sally","Suzan", "26459845","5645858","24652483","you@place.com","45 road","22/3/195","image.jpg"));
		   list.add(new Contact("Bob","richard", "26459845","5645858","24652483","you@place.com","45 road","22/3/195","image.jpg"));
		   list.add(new Contact("anne","McCaffery", "26459845","5645858","24652483","you@place.com","45 road","22/3/195","image.jpg"));
		   list.add(new Contact("The","Doctor", "1","1","1","the@doctor.time","tardis","22/3/-5","image.jpg"));
		   list.add(new Contact("sally","Blogs", "26459845","5645858","24652483","you@place.com","45 road","22/3/195","image.jpg"));
		   list.add(new Contact("Richard","Fran", "26459845","5645858","24652483","you@place.com","45 road","22/3/195","image.jpg"));
		   list.add(new Contact("Mum's","Uncle", "26459845","5645858","24652483","you@place.com","45 road","22/3/195","image.jpg"));
		   list.add(new Contact("The","Master", "2","2","2","the@master.time","somewhere","22/3/-20","image.jpg"));
		   list.add(new Contact("That","Blogs", "264","558","2463","you@place.com","45 road","22/3/195","image.jpg"));
		   list.add(new Contact("Albert","Longname", "245","564858","2483","you@place.com","13 road","22/3/195","image.jpg"));
		   list.add(new Contact("Pat","Postman", "21235","5567","78385","balck@cat.com","1 road","22/5/195","image.jpg"));
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
	   
	   public ArrayList<Contact> getList(){
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
		   
		   Collections.sort(contacts, new Comparator<Contact>() {
		        @Override public int compare(Contact c1, Contact c2) {
		            return (c1.getFirstName()).compareToIgnoreCase(c2.getFirstName());
		        }

		    });
		   
		   return contacts;
	   }
	   private ArrayList<Contact> sortListLastName(ArrayList<Contact> contacts) {
		   
		   Collections.sort(contacts, new Comparator<Contact>() {
		        @Override public int compare(Contact c1, Contact c2) {
		            return (c1.getLastName()).compareToIgnoreCase(c2.getLastName());
		        }

		    });
		   
		   return contacts;
	   }
}
