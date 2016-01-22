package com.uraal.cab.driver.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.uraal.cab.driver.R;


public class MapUtilities {
	
	
	public static Marker putCustomMarker(Context context, GoogleMap map, LatLng latLng, int pos){
		  Bitmap.Config conf = Bitmap.Config.ARGB_8888;
		  //Bitmap bmp = Bitmap.createBitmap(80, 80, conf);
		  BitmapFactory.Options dimensions = new BitmapFactory.Options(); 
		  dimensions.inJustDecodeBounds = true;
		  Bitmap mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_menu, dimensions);
		  int height = dimensions.outHeight;
		  int width =  dimensions.outWidth;
		  Bitmap bmp = Bitmap.createBitmap(width, height, conf);
		  Canvas canvas1 = new Canvas(bmp);

		  // paint defines the text color,
		  // stroke width, size
		  Paint color = new Paint();
		  color.setTextSize(35);
		  color.setFakeBoldText(true);
		  color.setColor(Color.WHITE);

		  //modify canvas
		  canvas1.drawBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_action_back), 0,0, color);
		  canvas1.drawText(""+pos, 33, 57, color);
		  //canvas1

		  //add marker to Map
		  return map.addMarker(new MarkerOptions().position(latLng)
		      .icon(BitmapDescriptorFactory.fromBitmap(bmp))
		      // Specifies the anchor to be at a particular point in the marker image.
		      .anchor(0.5f, 1));
		 }

}
