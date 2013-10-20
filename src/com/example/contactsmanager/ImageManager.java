package com.example.contactsmanager;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageManager {
	private static ImageManager INSTANCE;
	private static final String fileName = "image";
	//singleton method
	public static ImageManager getInstance() {
		   if (INSTANCE == null) {
			      INSTANCE = new ImageManager();
		   }
			  return INSTANCE;
	}
	
	public Bitmap getImage(String name){
		File imgFile = new File(name);
		Bitmap myBit = BitmapFactory.decodeFile(imgFile.getPath());
		return myBit;
	}
	
	public String saveImage(Bitmap b, Context c){
		
		FileOutputStream outputStream;
		int i = getFileNum(c);

		try {
		  outputStream = new FileOutputStream(c.getFilesDir().getPath() + fileName + i + ".png");
		  b.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
		  outputStream.close();
		} catch (Exception e) {
		  e.printStackTrace();
		}
		return c.getFilesDir().getPath()+fileName + i + ".png";
	}
	
	private int getFileNum(Context c){
		int i = 1;
		File file;
		Boolean exists = true;
		while(exists){
			file = new File(c.getFilesDir().getPath() + fileName + i + ".png");
			if(file.exists())      
				i++;
			else
				exists = false;
		}
		
		return i;
	}
	

}
