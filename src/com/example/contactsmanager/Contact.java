package com.example.contactsmanager;

public class Contact {
	private String _firstName;
	private String _lastName;
	private String _homeNumber;
	private String _workNumber;
	private String _mobileNumber;
	private String _emailAddress;
	private String _homeAddress;
	private String _DOB;
	private String _image;
	
	public Contact(String fName, String lName, String hNumber, String wNumber,
			String mNumber, String eAddress, String hAddress, String DOB, String image){
		_firstName = fName;
		_lastName = lName;
		_homeNumber = hNumber;
		_workNumber = wNumber;
		_mobileNumber = mNumber;
		_emailAddress = eAddress;
		_homeAddress = hAddress;
		_DOB = DOB;
		_image = image;
	}
	
	public String[] getDetails() {
		//Null pointer exception, be aware.
		String[] values = new String[9];
		values[0] = _firstName;
		values[1] = _lastName;
		values[2] = _homeNumber;
		values[3] = _workNumber;
		values[4] = _mobileNumber;
		values[5] = _emailAddress;
		values[6] = _homeAddress;
		values[7] = _DOB;
		values[8] = _image;
	
		return values;
	}
}
