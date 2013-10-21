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
	
	public Bitmap getImage(String name, int size){
		//following gets the size of the image
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(name, options);
		
		//calculate how much it needs to be shrunk to get the size wanted
		options.inSampleSize = calculateInSampleSize(options,size,size);
		
		//open file with appropriate size
		File imgFile = new File(name);
		options.inJustDecodeBounds = false;
		Bitmap myBit = BitmapFactory.decodeFile(imgFile.getPath(),options);
		return myBit;
	}
	
	public String saveImage(Bitmap b, Context c){
		
		//save image with 90% compression and return the name of the file
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
		
		//get next unused filename
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
	
	public boolean deleteImage(Contact c){
		String s = c.getImage();
		File file = new File(s);
		boolean deleted = file.delete();
		return deleted;
	}
	
	private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {

        // Calculate ratios of height and width to requested height and width
        final int heightRatio = Math.round((float) height / (float) reqHeight);
        final int widthRatio = Math.round((float) width / (float) reqWidth);

        // Choose the smallest ratio as inSampleSize value, this will guarantee
        // a final image with both dimensions larger than or equal to the
        // requested height and width.
        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
    }

    return inSampleSize;
}
	

}
