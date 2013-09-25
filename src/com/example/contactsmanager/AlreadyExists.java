package com.example.contactsmanager;

import android.app.Activity;
import android.os.Bundle;

public class AlreadyExists extends Activity{
	//notify the user already exists
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.already_exists);
    }
}
