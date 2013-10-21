package com.example.contactsmanager;

import java.lang.ref.WeakReference;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

public class BitmapWorkerOpen extends AsyncTask<String, Void, Bitmap>{
	private  WeakReference<ImageView> imageViewReference;

	
	
	public BitmapWorkerOpen(ImageView imageView) {
		// Use a WeakReference to ensure the ImageView can be garbage collected
        imageViewReference = new WeakReference<ImageView>(imageView);
	}


	@Override
	protected Bitmap doInBackground(String... params) {
		ImageManager manager = ImageManager.getInstance();
		return manager.getImage(params[0], 60);
		
	}
	
	 @Override
	    protected void onPostExecute(Bitmap bitmap) {
	        if (imageViewReference != null && bitmap != null) {
	            final ImageView imageView = imageViewReference.get();
	            if (imageView != null) {
	                imageView.setImageBitmap(bitmap);
	            }
	        }
	    }

}
