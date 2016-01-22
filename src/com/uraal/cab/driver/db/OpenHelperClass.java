package com.uraal.cab.driver.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.uraal.cab.driver.baseClasses.Constants;


public class OpenHelperClass extends SQLiteOpenHelper implements Constants
{
	public static final String DB_NAME="map_db.sqlite";
	private static final String TAG = "Open Helper";

	private SQLiteDatabase myDataBase; 

	private Context myContext;

	private String DB_PATH="/data/data/com.wms.noto.main/databases/";

	private final String pathPrefix = "databases/";
	
	private final String tbl_cows = "tbl_cows";
	
	public OpenHelperClass(Context context) 
	{	 
		
		
		/*super(context, DB_NAME, null, 1);
		this.myContext = context;*/
		//DB_PATH = this.myContext.getFilesDir().getPath()+"/"+pathPrefix;

		super(context, DB_NAME, null, 1);// 1? its Database Version
		Log.i("OpenHelperClass", "OpenHelperClass");
		if(android.os.Build.VERSION.SDK_INT >= 17){
			DB_PATH = context.getApplicationInfo().dataDir + "/"+pathPrefix;         
		}
		else
		{
			DB_PATH = "/data/data/" + context.getPackageName() + "/"+pathPrefix;
		}
		this.myContext = context;
	}	


	public SQLiteDatabase openDataBase() throws SQLException
	{
		Log.i("openDataBase", "openDataBase");
		//Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		return myDataBase;
	}

	/* //Open the database, so we can query it
    public boolean openDataBase() throws SQLException
    {
        String mPath = DB_PATH + DB_NAME;
        //Log.v("mPath", mPath);
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDataBase != null;
    }	*/

	
	@Override
	public synchronized void close() 
	{
		Log.i("synchronized", "synchronized");
		if(myDataBase != null)
			myDataBase.close();

		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{	Log.i("onCreate", "onCreate");
	        //Create start route table
			String CREATE_START_ROUTE_TABLE = "CREATE TABLE " + Constants.TABLE_START_ROUTE + "("
					+ Constants.COLUMN_ROUTE_NAME + " TEXT," 
					+ Constants.COLUMN_LAT + " DOUBLE,"
					+ Constants.COLUMN_LOG + " DOUBLE,"
					+ Constants.COLUMN_CREATED_DATE + " long,"
					+ Constants.COLUMN_IS_COMPLETED + " INTEGER,"
					+ Constants.COLUMN_UPDATE_DATE + " long,"
					+ Constants.COLUMN_ROW_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE"+ ")";
			db.execSQL(CREATE_START_ROUTE_TABLE);
		
			//Create  end route table
			String CREATE_END_ROUTE_TABLE = "CREATE TABLE " + Constants.TABLE_END_ROUTE + "("
					+ Constants.COLUMN_ROUTE_NAME + " TEXT," 
					+ Constants.COLUMN_LAT + " DOUBLE,"
					+ Constants.COLUMN_LOG + " DOUBLE,"
					+ Constants.COLUMN_CREATED_DATE + " long,"
					+ Constants.COLUMN_IS_COMPLETED + " INTEGER,"
					+ Constants.COLUMN_NOTE_ID + " INTEGER,"
					+ Constants.COLUMN_UPDATE_DATE + " long,"
					+ Constants.COLUMN_ROW_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE,"
					+ Constants.COLUMN_ROW_ID_OF_START_ROUTE + " INTEGER"+ ")";
			db.execSQL(CREATE_END_ROUTE_TABLE);
				
			//Create  favorite list table
			String CREATE_FAVORITE_LIST = "CREATE TABLE " + Constants.TABLE_FAVORITE_LIST + "("
					+ Constants.COLUMN_ROUTE_NAME + " TEXT," 
					+ Constants.COLUMN_LAT + " DOUBLE,"
					+ Constants.COLUMN_LOG + " DOUBLE,"
					+ Constants.COLUMN_IS_FAV + " INTEGER,"
					+ Constants.COLUMN_CREATED_DATE + " long,"
					+ Constants.COLUMN_UPDATE_DATE + " long,"
					+ Constants.COLUMN_NOTE_ID + " INTEGER,"
					+ Constants.COLUMN_TITLE + " TEXT,"
					+ Constants.COLUMN_ROW_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE"+ ")";
			db.execSQL(CREATE_FAVORITE_LIST);
		
			//Create note table
			String CREATE_NOTE = "CREATE TABLE " + Constants.TABLE_NOTE + "("
					+ Constants.COLUMN_NOTE_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE," 
					+ Constants.COLUMN_NOTE_NAME + " TEXT,"
					+ Constants.COLUMN_IMAGE_URL + " TEXT,"	
					+ Constants.COLUMN_CREATED_DATE + " long,"
					+ Constants.COLUMN_UPDATE_DATE + " long,"
					+ Constants.COLUMN_NOTES_OF + " VARCHAR,"
					+ Constants.COLUMN_NOTES_OF_ID + " INTEGER,"
					+ Constants.COLUMN_CAPTION + " TEXT,"
					+ Constants.COLUMN_IMG_COUNT + " INTEGER,"
					+ Constants.COLUMN_NOTE_TEXT + " TEXT,"
					+ Constants.COLUMN_IS_IMG + " INTEGER"+ ")";
			db.execSQL(CREATE_NOTE	);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		Log.i("onUpgrade", "onUpgrade");
		if(newVersion > oldVersion){
			db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_START_ROUTE);
			db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_END_ROUTE);
			db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_FAVORITE_LIST);
			db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NOTE);

			//Create tables again
			onCreate(db);		
		}
	}


	//Copy Database
	public void createDataBase() throws IOException
	{
		Log.i("createDataBase", "createDataBase");
		//If database not exists copy it from the assets
		boolean mDataBaseExist = checkDataBase();
		if(!mDataBaseExist)
		{
			this.getReadableDatabase();
			this.close();
			try 
			{
				//Copy the database from assests
				copyDataBase();
				Log.e(TAG, "createDatabase database created");
			} 
			catch (IOException mIOException) 
			{
				throw new Error("ErrorCopyingDataBase");
			}
		}
	}
	//Check that the database exists here: /data/data/your package/databases/Da Name
	private boolean checkDataBase()
	{
		Log.i("checkDataBase", "checkDataBase");
		File dbFile = new File(DB_PATH + DB_NAME);
		//Log.v("dbFile", dbFile + "   "+ dbFile.exists());
		return dbFile.exists();
	}

	//Copy the database from assets
	private void copyDataBase() throws IOException
	{
		Log.i("copyDataBase", "copyDataBase     ");
		InputStream mInput = myContext.getAssets().open(pathPrefix+DB_NAME);
		String outFileName = DB_PATH + DB_NAME;
		OutputStream mOutput = new FileOutputStream(outFileName);
		byte[] mBuffer = new byte[1024];
		int mLength;
		while ((mLength = mInput.read(mBuffer))>0)
		{
			mOutput.write(mBuffer, 0, mLength);
		}
		mOutput.flush();
		mOutput.close();
		mInput.close();
	}
}

