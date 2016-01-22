package com.uraal.cab.driver.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Environment;
import android.util.Log;

public class GetFileClass 
{

	private static final String TAG = "FormFuture";
	private static GetFileClass getFileClass = null;
	private GetFileClass(){}


	public static GetFileClass getInstance(){
		if(getFileClass == null)
			getFileClass = new GetFileClass();

		return getFileClass;
	}
	private String getAlbumName() 
	{
		return "tempTask";
	}

	private File getAlbumDir(AlbumStorageDirFactory mAlbumStorageDirFactory) 
	{
		File storageDir = null;

		if (Environment.MEDIA_MOUNTED.equalsIgnoreCase(Environment.getExternalStorageState())) 
		{			
			storageDir = mAlbumStorageDirFactory.getAlbumStorageDir(getAlbumName());

			if (storageDir != null) 
			{
				if(!storageDir.mkdirs()) 
				{
					if (! storageDir.exists())
					{
						Log.d(TAG, "failed to create directory");
						return null;
					}
				}
			}

		} else 
		{
			Log.v(TAG, "External storage is not mounted READ/WRITE.");
		}

		return storageDir;
	}

	private File createFile(AlbumStorageDirFactory mAlbumStorageDirFactory, String filePrefix, String extension, boolean isNewFile) throws IOException 
	{
		// Create an image file name
		String timeStamp = "";
		if(isNewFile)
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss" , Locale.getDefault()).format(new Date());

		String imageFileName = filePrefix+"_" + timeStamp + "_";
		File albumF = getAlbumDir(mAlbumStorageDirFactory);
		File imageF = File.createTempFile(imageFileName, extension, albumF);
		return imageF;
	}

	private File setUpFile(AlbumStorageDirFactory mAlbumStorageDirFactory, String filePrefix, String extension, boolean isNewFile) throws IOException 
	{		
		File f = createFile(mAlbumStorageDirFactory, filePrefix, extension, isNewFile);		
		return f;
	}

	public File getFile(AlbumStorageDirFactory mAlbumStorageDirFactory, String filePrefix, String extension, boolean isNewFile)
	{
		File f = null;

		try 
		{
			f = setUpFile(mAlbumStorageDirFactory, filePrefix, extension, isNewFile);			
			System.out.println("AbsolutePath  : "+f.getAbsolutePath());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			f = null;			
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
		return f;
	}

	/*private File createTextFile(AlbumStorageDirFactory mAlbumStorageDirFactory) throws IOException 
	{
		// Create an image file name
		//String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String txtFileName = "TaskFile" + "_";
		File albumF = getAlbumDir(mAlbumStorageDirFactory);
		File txtF = File.createTempFile(txtFileName, ".txt", albumF);
		return txtF;
	}*/

	/*public File getTxtFile(AlbumStorageDirFactory mAlbumStorageDirFactory)
	{
		File f = null;

		try 
		{
			f = createTextFile(mAlbumStorageDirFactory);			
			System.out.println("AbsolutePath  : "+f.getAbsolutePath());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			f = null;			
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
		return f;
	}*/

}
