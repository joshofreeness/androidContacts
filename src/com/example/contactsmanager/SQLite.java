package com.example.contactsmanager;



import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLite extends SQLiteOpenHelper{
	
	final static String DATABASE_NAME = "ContactDetails.db";
	final String TABLE_NAME = "contacts";
	final String CONTACT_FIRST_NAME = "first";
	final String CONTACT_LAST_NAME = "last";
	final String CONTACT_MOBILE_NO = "mob_no";
	final String CONTACT_HOME_NO = "home_no";
	final String CONTACT_WORK_NO = "work_no";
	final String CONTACT_EMAIL = "email";
	final String CONTACT_DOB = "dob";
	final String CONTACT_ADDRESS = "address";
	final String CONTACT_PHOTO = "photo";
	
	
	private final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
			+ CONTACT_FIRST_NAME + " TEXT, "
			+ CONTACT_LAST_NAME + " TEXT, "
			+ CONTACT_MOBILE_NO + " TEXT, "
			+ CONTACT_HOME_NO + " TEXT, "
			+ CONTACT_WORK_NO + " TEXT, "
			+ CONTACT_EMAIL + " TEXT, "
			+ CONTACT_DOB + " TEXT, "
			+ CONTACT_ADDRESS + " TEXT, "
			+ CONTACT_PHOTO + " TEXT);";
	
	SQLiteDatabase db;

	public SQLite(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DATABASE_NAME, null, 1);
		
	}

	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
			   db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//called when the version of the existing db is less than the current
        Log.w(this.getClass().getName(), "Upgrading db from "+oldVersion+" to "+newVersion);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    
		
	}
	
	// Adding new contact
	public void addContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		String[] cValues = contact.getDetails();
	    ContentValues values = new ContentValues();
	    
	    values.put(CONTACT_FIRST_NAME, cValues[0]); 
	    values.put(CONTACT_LAST_NAME, cValues[1]); 
	    values.put(CONTACT_HOME_NO, cValues[2]); 
	    values.put(CONTACT_WORK_NO, cValues[3]); 
	    values.put(CONTACT_MOBILE_NO, cValues[4]); 
	    values.put(CONTACT_EMAIL, cValues[5]); 
	    values.put(CONTACT_HOME_NO, cValues[6]); 
	    values.put(CONTACT_DOB, cValues[7]); 
	    values.put(CONTACT_PHOTO, cValues[8]); 
	 
	    // Inserting Row
	    try {  
	        db.beginTransaction();
	        db.insertOrThrow(TABLE_NAME, null, values);
	        db.setTransactionSuccessful();
	        } 
	    finally {
	        db.endTransaction();
	        }
	    
	    db.close(); // Closing database connection
	}
	 
	// Getting single contact
	public Contact getContact(int id) {
		return null;
	    }
	 
	// Getting All Contacts
	public List<Contact> getAllContacts() {
		List<Contact> contactList = new ArrayList<Contact>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + TABLE_NAME;
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {

	            // Adding contact to list
	            contactList.add(cursorToContact(cursor));
	        } while (cursor.moveToNext());
	    }
	 
	    // return contact list
		contactList.add(new Contact("Bob","Suzan", "26459845","5645858","24652483","you@place.com","45 road","22/3/195","image.jpg"));

	    return contactList;
	}
	
	 
	// Getting contacts Count
	public int getContactsCount() {
		return 0;}
	// Updating single contact
	public int updateContact(Contact contact) {
		return 0;}
	 
	// Deleting single contact
	public void deleteContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(TABLE_NAME, KEY_ID + " = ?",
	            new String[] { String.valueOf(contact.getID()) });
	    db.close();
	}
	
	private Contact cursorToContact (Cursor cursor){
		
		String fname = cursor.getString(0);
        String lname =cursor.getString(1);
        String mnum = cursor.getString(2);
        String hnum = cursor.getString(3);
        String wnum = cursor.getString(4);
        String email = cursor.getString(5);
        String dob = cursor.getString(6);
        String address = cursor.getString(7);
        String photo = cursor.getString(8);
        
        Contact contact = new Contact(fname,lname,hnum,wnum,mnum,email,address,dob,photo); 
		return contact;
	}

}
