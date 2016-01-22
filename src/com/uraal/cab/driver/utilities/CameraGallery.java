package com.uraal.cab.driver.utilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uraal.cab.driver.R;
import com.uraal.cab.driver.baseClasses.BaseFragment;
import com.uraal.cab.driver.baseClasses.BaseFragmentActivity;
import com.uraal.cab.driver.baseClasses.MyApplication;

import eu.janmuller.android.simplecropimage.CropImage;

public class CameraGallery {
	public static final int REQUEST_CODE_GALLERY      = 0x1;
	public static final int REQUEST_CODE_TAKE_PICTURE = 0x2;
	public static final int REQUEST_CODE_CROP_IMAGE   = 0x3;
	private static final String TAG = CameraGallery.class.getSimpleName();
	public static final String img_url = "imgUrl";
	public static final String bitmap = "bitmap";

	private AlbumStorageDirFactory mAlbumStorageDirFactory;
	public static File mFileTemp;

	public CameraGallery() {

	} 

	private void initDirectory(){
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO)
		{
			mAlbumStorageDirFactory = new FroyoAlbumDirFactory();
		}
		else 
		{
			mAlbumStorageDirFactory = new BaseAlbumDirFactory();
		}    
	}

	public void openImageSource(final Context context, final BaseFragment fragment)
	{ 
		initDirectory();

		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.custom_dialog_img_source, null);
		RelativeLayout root = (RelativeLayout) view.findViewById(R.id.root);
		TextView textView = (TextView) view.findViewById(R.id.textV_confirm_msg);  
		Button btn_Camera = (Button) view.findViewById(R.id.btn_1);
		Button btn_Gallery = (Button) view.findViewById(R.id.btn_2);
      //  btn_Gallery.setVisibility(View.GONE);
		textView.setText(context.getString(R.string.chooseImg));
		btn_Camera.setText("Camera");
		btn_Gallery.setText("Gallery");

		RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams((MyApplication.getApplication().getWidthPixel() * 60)/100 , RelativeLayout.LayoutParams.WRAP_CONTENT);   
		root.setLayoutParams(param);
		dialog.setContentView(view);
		try
		{
			dialog.show();
		}
		catch (Throwable e) 
		{  
			e.printStackTrace();
		}

		btn_Camera.setOnClickListener(new OnClickListener() 
		{    
			@Override
			public void onClick(View v) 
			{ 
				dialog.dismiss();
				takePicture(context, fragment);
			}
		});

		btn_Gallery.setOnClickListener(new OnClickListener() 
		{    
			@Override
			public void onClick(View v) 
			{
				dialog.dismiss();      
				openGallery(context, fragment);
			}
		});
	}

	private void takePicture(Context context, BaseFragment fragment) 
	{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		try 
		{
			mFileTemp = GetFileClass.getInstance().getFile(mAlbumStorageDirFactory, "Img", ".jpg" , true);
			Uri mImageCaptureUri = Uri.fromFile(mFileTemp);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
			//intent.putExtra("return-data", true);
			if (fragment == null) {
				Log.i("FRAGMENT", "null");
				((BaseFragmentActivity)context).startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE);
			}else {
				Log.i("FRAGMENT", "not null");
				fragment.startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE);
			}
			
		} 
		catch (ActivityNotFoundException e) 
		{
			Log.d(TAG, "cannot take picture", e);
		}
	}

	private void openGallery(Context context, BaseFragment fragment) 
	{
		
		Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
		photoPickerIntent.setType("image/*");
		if (fragment == null) {
			((BaseFragmentActivity)context).startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY);
		}else {
			fragment.startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY);
		}
		
	}

	public void startCropImage(Context context, BaseFragment fragment, String imagePath) {

		Intent intent = new Intent(context, CropImage.class);
		
		intent.putExtra(CropImage.IMAGE_PATH, imagePath);
		intent.putExtra(CropImage.SCALE, true);

		intent.putExtra(CropImage.ASPECT_X, 2);
		intent.putExtra(CropImage.ASPECT_Y, 2);
    if (fragment == null) {
	   ((BaseFragmentActivity)context).startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }else {
	fragment.startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }
		
	}

	public  void copyStream(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = input.read(buffer)) != -1) {
			output.write(buffer, 0, bytesRead);
		}
	}


}
