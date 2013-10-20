package com.example.contactsmanager;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;



public class ImageManager {

	   private static ImageManager INSTANCE;
	   private static final String fileName = "contactImage";
	   //Singleton pattern
	   private ImageManager() {}

	   public static ImageManager getInstance() {
		   if (INSTANCE == null) {
			      INSTANCE = new ImageManager();
		   }
			  return INSTANCE;
	 }
	
	   public String saveImage(Bitmap bit, Context c){
		   //TODO: //save image and return URI
		   String name = getFileName(c);
		   File file = new File(c.getFilesDir(), name);
		   
		   try {
		       FileOutputStream out = new FileOutputStream(file);
		       bit.compress(Bitmap.CompressFormat.PNG, 90, out);
		       out.close();
		} catch (Exception e) {
		       e.printStackTrace();
		       return null;
		}
		   return name;
	   }
	
	   public Bitmap getImage(String uri){
		   //TODO: return image according to string
		   return null;
	   }
	   
	   private String getFileName(Context c){
		   int i = 1;
		   File file = null;
		   Boolean exists = true;
		   while (exists){
			   file = new File(c.getFilesDir(),fileName + i );
			   if(file.exists()) {
				   i++;
			   } else {
				   exists = false;
			   }
		   }
		   return fileName + i;
	   }
}
