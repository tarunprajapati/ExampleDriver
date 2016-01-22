package com.uraal.cab.driver.db;

import java.io.IOException;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.uraal.cab.driver.baseClasses.Constants;
import com.uraal.cab.driver.beanClasses.EndRoute;
import com.uraal.cab.driver.beanClasses.Notes;
import com.uraal.cab.driver.beanClasses.Notifications;
import com.uraal.cab.driver.beanClasses.StartRoute;



public class DataBaseQueriesClass implements Constants {
	private static final String TAG = "DataBaseQuerires";
	private static DataBaseQueriesClass dbQueries;

	public static DataBaseQueriesClass getInstance() {
		if (dbQueries == null)
			dbQueries = new DataBaseQueriesClass();
		return dbQueries;
	}

	// private static ContentValues values;
	// private static SQLiteDatabase db;
	// private static OpenHelperClass openHelperClass;
	// static SQLiteDatabase db = null;
	public SQLiteDatabase getWritableDatabase(Context context) {
		// if(db == null){
		OpenHelperClass openHelperClass = new OpenHelperClass(context);
		SQLiteDatabase db = openHelperClass.getWritableDatabase();
		// }
		return db;
	}

	public SQLiteDatabase getReadableDataBase(Context context) {
		// if(db == null){
		OpenHelperClass openHelperClass = new OpenHelperClass(context);
		SQLiteDatabase db = openHelperClass.getReadableDatabase();
		// }
		return db;
	}

	public void deleteIntoTable(SQLiteDatabase dbLoc, String deleteQuery) {
		dbLoc.rawQuery(deleteQuery, null);
		closeResources(null, dbLoc);
	}

	public void createDatabase(Context mContext) throws SQLException {
		OpenHelperClass mDbHelper = new OpenHelperClass(mContext);
		try {
			mDbHelper.createDataBase();
		} catch (IOException mIOException) {
			Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
			throw new Error("UnableToCreateDatabase");
		}
	}

	public void closeResources(Cursor cursor, SQLiteDatabase db) {
		if (cursor != null) {
			cursor.close();
		}
		if (db != null) {
			db.close();
		}
	}

	public boolean isUpdateNeed(SQLiteDatabase db, String tblName,
			String columnName, String id) {
		Cursor cursor = null;
		try {
			// Select All Query
			String selectQuery = "SELECT * FROM " + tblName + " WHERE "
					+ columnName + " = '" + id + "'";
			Log.i("getAnimal", "=" + selectQuery);
			cursor = db.rawQuery(selectQuery, null);
		} catch (SQLiteException e) {
			Log.i("update", "=" + e.getMessage());
		} catch (Exception e) {
			Log.i("update", "=" + e.getMessage());
		}
		if (cursor != null && cursor.getCount() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*public void saveNotes(Context context, String caption,String imgUrl, String address,  int id, String noteOf){
		long createdDate = UtilsClass.getCurrentTimeInMili();
		Notes notes = new Notes();
		notes.setAddress(address);
		notes.setCaption(caption);
		notes.setCreatedDate(createdDate);
		notes.setImageUrl(imgUrl);
		notes.setNotesOf(noteOf);
		notes.setNotesOfId(id);
		notes.setUpdatedDate(createdDate);
		DataBaseQueriesClass.getInstance().addNotes(notes, context);
			
	}*/

	public void update(SQLiteDatabase db, String tblName, String columnName,
			String columnValue, ContentValues cv) {
		try {
			db.update(tblName, cv, columnName + " = ?",
					new String[] { columnValue });
		} catch (SQLiteException e) {
			Log.i("update", "=" + e.getMessage());
		} catch (Exception e) {
			Log.i("update", "=" + e.getMessage());
		}
	}

	// Adding new favorites
	public long addFavourateList(EndRoute address, Context context) {
		SQLiteDatabase db = getWritableDatabase(context);
		Log.i("addAnimal", "addAnimal");
		ContentValues values;

		long ret = QUARY_RESPONSE_LONG;
		try {
			values = new ContentValues();
			values.put(COLUMN_ROUTE_NAME, address.getAddress());
			values.put(COLUMN_LAT, address.getLat());
			values.put(COLUMN_LOG, address.getLng());
			values.put(COLUMN_IS_FAV, address.is_fav());
			values.put(COLUMN_CREATED_DATE, address.getCreatedDate());
			values.put(COLUMN_UPDATE_DATE, address.getUpdatedDate());
			//values.put(COLUMN_NOTE_ID, address.getNoteId());
			values.put(COLUMN_TITLE, address.getTitle());
			ret = db.insert(TABLE_FAVORITE_LIST, null, values);

			/*
			 * if (isUpdateNeed(db, TABLE_ANIMAL_DETAILS, AnimalNumber,
			 * ""+animal.getAnimalNumber())) { Log.i("isUpdateNeed", "if");
			 * update(db, TABLE_ANIMAL_DETAILS, AnimalNumber,
			 * ""+animal.getAnimalNumber(), values); }else {
			 * Log.i("isUpdateNeed", "else"); // Inserting Row ret =
			 * db.insert(TABLE_ANIMAL_DETAILS, null, values); }
			 */

		} catch (SQLiteException e) {
			ret = QUARY_RESPONSE_LONG;
			Log.i("addAnimal", "=" + e.getMessage());
		} catch (Exception e) {
			ret = QUARY_RESPONSE_LONG;
			Log.i("addAnimal", "=" + e.getMessage());
		} finally {
			closeResources(null, db);
		}

		// return row id if any error it will return -1
		return ret;
	}

	// Updating the note id in favorites list table
	public int updateFavoriteNoteId(long id, Context context, int noteId) {

		Log.i("updateFavoruiteNoteId", "updateFavoruiteNoteId");
		SQLiteDatabase db = getWritableDatabase(context);
		int ret = QUARY_RESPONSE_INT;
		try {

			ContentValues values = new ContentValues();
			values.put(COLUMN_NOTE_ID, noteId);

			ret = db.update(TABLE_FAVORITE_LIST, values, COLUMN_CREATED_DATE
					+ " = ?", new String[] { String.valueOf(id) });

		} catch (SQLiteException e) {
			ret = QUARY_RESPONSE_INT;
			Log.i("updateFavoruiteNoteId", "=" + e.getMessage());

		} catch (Exception e) {
			ret = QUARY_RESPONSE_INT;
			Log.i("updateFavoruiteNoteId", "=" + e.getMessage());

		} finally {
			closeResources(null, db);
		}
		return ret;

	}

	// Get favourate list
	public ArrayList<EndRoute> getFavourateList(Context context , String quary) {

		Cursor cursor = null;
		EndRoute address = null;
		SQLiteDatabase db = getReadableDataBase(context);
		ArrayList<EndRoute> arrayList = new ArrayList<EndRoute>();
		try {
			// Select All Query
			/*String selectQuery = "SELECT * FROM "
					+ Constants.TABLE_FAVORITE_LIST;*/
			Log.i("getFavourateList", "=" + quary);
			cursor = db.rawQuery(quary, null);
			if (cursor.moveToFirst()) {
				do {
					address = new EndRoute();

					/*int notes = getNotesRowCount(cursor.getInt(8),NOTE_OF_FAV, context);
					Log.i("image count", "=="+notes);
					address.setImgCount(notes);*/


					address.setAddress(cursor.getString(0));
					address.setLat(cursor.getDouble(1));
					address.setLng(cursor.getDouble(2));
					address.set_fav(cursor.getInt(3));
					address.setCreatedDate(cursor.getLong(4));
					address.setUpdatedDate(cursor.getLong(5));
					//address.setNoteId(cursor.getInt(6));
					address.setTitle(cursor.getString(7));
					address.setRowId(cursor.getInt(8));
					address.setDistance("KM Away");
					Log.i("ADDRESSSSSSSSSSSS", address.toString());
					arrayList.add(address);
				} while (cursor.moveToNext());
			}

		} catch (SQLiteException e) {
			Log.i("getFavourateList", "=" + e.getMessage());
		} catch (Exception e) {
			Log.i("getFavourateList", "=" + e.getMessage());
		} finally {
			closeResources(cursor, db);
		}
		// return contact list
		return arrayList;

	}

	
	// Get favourate row id
    public int getFavouritesRowId(long createdDate, Context context){
		
		Cursor cursor = null;
		StartRoute address = null;
		SQLiteDatabase db = getReadableDataBase(context);
		int rowId = 0;
		try {
			// Select All Query
			String selectQuery = "SELECT * FROM " + TABLE_FAVORITE_LIST + " WHERE "
					+ COLUMN_CREATED_DATE + " = '" + createdDate + "'";
			Log.i("getFavouritesRowId", "=" + selectQuery);
			cursor = db.rawQuery(selectQuery, null);
			Log.i("getFavouritesRowId cursor", "=" + cursor.getCount());
			if (cursor.moveToFirst()) {
				
				rowId = cursor.getInt(8);
				Log.i("getFavouritesRowId rowid", "=" +rowId);
				
			}

		} catch (SQLiteException e) {
			Log.i("SQLiteException", "=" + e.getMessage());
		} catch (Exception e) {
			Log.i("Exception", "=" + e.getMessage());
		} finally {
			closeResources(cursor, db);
		}
		// return contact list
		return rowId;
		
	}
    
 // Get favourate title
    public String getFavouritesTitle(int rowId, Context context){
		
		Cursor cursor = null;
		StartRoute address = null;
		SQLiteDatabase db = getReadableDataBase(context);
		String title = "";
		try {
			// Select All Query
			String selectQuery = "SELECT * FROM " + TABLE_FAVORITE_LIST + " WHERE "
					+ COLUMN_ROW_ID + " = '" + rowId + "'";
			Log.i("getFavouritesTitle", "=" + selectQuery);
			cursor = db.rawQuery(selectQuery, null);
			Log.i("getFavouritesRowId cursor", "=" + cursor.getCount());
			if (cursor.moveToFirst()) {
				
				title = cursor.getString(7);
				Log.i("getFavouritesTitle title", "=" +title);
				
			}

		} catch (SQLiteException e) {
			Log.i("SQLiteException", "=" + e.getMessage());
		} catch (Exception e) {
			Log.i("Exception", "=" + e.getMessage());
		} finally {
			closeResources(cursor, db);
		}
		// return contact list
		return title;
		
	}
	// Delete favorites
	public int deleteFavorites(long createdDate, Context context) {

		SQLiteDatabase db = getWritableDatabase(context);
		int ret = 0;
		try {

			ret = db.delete(TABLE_FAVORITE_LIST, COLUMN_CREATED_DATE + " = ?",
					new String[] { String.valueOf(createdDate) });

		} catch (SQLiteException e) {
			ret = QUARY_RESPONSE_INT;
			Log.i("updateNotes", "=" + e.getMessage());

		} catch (Exception e) {
			ret = QUARY_RESPONSE_INT;
			Log.i("updateNotes", "=" + e.getMessage());

		} finally {
			closeResources(null, db);
		}
		// the number of rows affected if a whereClause is passed in, 0
		// otherwise. To remove all rows and get a count pass "1" as the
		// whereClause.
		// Any exception return -1
		return ret;

	}

	// Adding new start route
	public long addStartRoute(StartRoute address, Context context) {
		SQLiteDatabase db = getWritableDatabase(context);
		Log.i("addStartRoute", "addStartRoute");
		ContentValues values;

		long ret = QUARY_RESPONSE_LONG;
		try {
			values = new ContentValues();
			values.put(COLUMN_ROUTE_NAME, address.getAddress());
			values.put(COLUMN_LAT, address.getLat());
			values.put(COLUMN_LOG, address.getLng());
			values.put(COLUMN_CREATED_DATE, address.getCreatedDate());
			values.put(COLUMN_IS_COMPLETED, address.is_completed());
			values.put(COLUMN_UPDATE_DATE, address.getUpdatedDate());

			ret = db.insert(TABLE_START_ROUTE, null, values);

			/*
			 * if (isUpdateNeed(db, TABLE_ANIMAL_DETAILS, AnimalNumber,
			 * ""+animal.getAnimalNumber())) { Log.i("isUpdateNeed", "if");
			 * update(db, TABLE_ANIMAL_DETAILS, AnimalNumber,
			 * ""+animal.getAnimalNumber(), values); }else {
			 * Log.i("isUpdateNeed", "else"); // Inserting Row ret =
			 * db.insert(TABLE_ANIMAL_DETAILS, null, values); }
			 */

		} catch (SQLiteException e) {
			ret = QUARY_RESPONSE_LONG;
			Log.i("addStartRoute", "=" + e.getMessage());
		} catch (Exception e) {
			ret = QUARY_RESPONSE_LONG;
			Log.i("addStartRoute", "=" + e.getMessage());
		} finally {
			closeResources(null, db);
		}

		// return row id if any error it will return -1
		return ret;
	}

	// Get start route list
	public ArrayList<StartRoute> getStartRouteList(Context context) {

		Cursor cursor = null;
		StartRoute address = null;
		SQLiteDatabase db = getReadableDataBase(context);
		ArrayList<StartRoute> arrayList = new ArrayList<StartRoute>();
		try {
			// Select All Query
			String selectQuery = "SELECT * FROM " + Constants.TABLE_START_ROUTE;
			Log.i("getStartRouteList", "=" + selectQuery);
			cursor = db.rawQuery(selectQuery, null);
			if (cursor.moveToFirst()) {
				do {
					address = new StartRoute();
					address.setAddress(cursor.getString(0));
					address.setLat(cursor.getDouble(1));
					address.setLng(cursor.getDouble(2));
					address.setCreatedDate(cursor.getLong(3));
					address.setIs_completed(cursor.getInt(4) == 0 ? false: true);
					address.setUpdatedDate(cursor.getLong(5));
					address.setRowId(cursor.getInt(6));
					arrayList.add(address);
				} while (cursor.moveToNext());
			}

		} catch (SQLiteException e) {
			Log.i("getStartRouteList", "=" + e.getMessage());
		} catch (Exception e) {
			Log.i("getStartRouteList", "=" + e.getMessage());
		} finally {
			closeResources(cursor, db);
		}
		// return contact list
		return arrayList;

	}
	
	public int getStartRouteRowId(long createdDate, Context context){
		
		Cursor cursor = null;
		StartRoute address = null;
		SQLiteDatabase db = getReadableDataBase(context);
		int rowId = 0;
		try {
			// Select All Query
			String selectQuery = "SELECT * FROM " + TABLE_START_ROUTE + " WHERE "
					+ COLUMN_CREATED_DATE + " = '" + createdDate + "'";
			Log.i("getStartRouteRowId", "=" + selectQuery);
			cursor = db.rawQuery(selectQuery, null);
			Log.i("getStartRouteRowId cursor", "=" + cursor.getCount());
			if (cursor.moveToFirst()) {
				
				rowId = cursor.getInt(6);
				Log.i("getStartRouteRowId rowid", "=" +rowId);
				
			}

		} catch (SQLiteException e) {
			Log.i("SQLiteException", "=" + e.getMessage());
		} catch (Exception e) {
			Log.i("Exception", "=" + e.getMessage());
		} finally {
			closeResources(cursor, db);
		}
		// return contact list
		return rowId;
		
	}
	
     public String getStartAdress(int rowId, Context context){
		
		Cursor cursor = null;
		StartRoute address = null;
		SQLiteDatabase db = getReadableDataBase(context);
		String startAdress = "";
		try {
			// Select All Query
			String selectQuery = "SELECT * FROM " + TABLE_START_ROUTE + " WHERE "
					+ COLUMN_ROW_ID + " = '" + rowId + "'";
			Log.i("getStartAdress", "=" + selectQuery);
			cursor = db.rawQuery(selectQuery, null);
			Log.i("getStartAdress cursor", "=" + cursor.getCount());
			if (cursor.moveToFirst()) {
				
				startAdress = cursor.getString(0);
				Log.i("getStartAdress startAdress", "=" +startAdress);
				
			}

		} catch (SQLiteException e) {
			Log.i("SQLiteException", "=" + e.getMessage());
		} catch (Exception e) {
			Log.i("Exception", "=" + e.getMessage());
		} finally {
			closeResources(cursor, db);
		}
		// return contact list
		return startAdress;
		
	}

	// Adding new end route
	public long addEndRoute(EndRoute address, Context context) {
		SQLiteDatabase db = getWritableDatabase(context);
		Log.i("addEndRoute", "addEndRoute");
		ContentValues values;

		long ret = QUARY_RESPONSE_LONG;
		try {
			values = new ContentValues();
			values.put(COLUMN_ROUTE_NAME, address.getAddress());
			values.put(COLUMN_LAT, address.getLat());
			values.put(COLUMN_LOG, address.getLng());
			values.put(COLUMN_CREATED_DATE, address.getCreatedDate());
			values.put(COLUMN_IS_COMPLETED, address.is_completed());
			values.put(COLUMN_UPDATE_DATE, address.getUpdatedDate());
			//values.put(COLUMN_NOTE_ID, address.getNoteId());
			values.put(COLUMN_ROW_ID_OF_START_ROUTE, address.getRowIdOfStartRoute());

			ret = db.insert(TABLE_END_ROUTE, null, values);

			/*
			 * if (isUpdateNeed(db, TABLE_ANIMAL_DETAILS, AnimalNumber,
			 * ""+animal.getAnimalNumber())) { Log.i("isUpdateNeed", "if");
			 * update(db, TABLE_ANIMAL_DETAILS, AnimalNumber,
			 * ""+animal.getAnimalNumber(), values); }else {
			 * Log.i("isUpdateNeed", "else"); // Inserting Row ret =
			 * db.insert(TABLE_ANIMAL_DETAILS, null, values); }
			 */

		} catch (SQLiteException e) {
			ret = QUARY_RESPONSE_LONG;
			Log.i("addEndRoute", "=" + e.getMessage());
		} catch (Exception e) {
			ret = QUARY_RESPONSE_LONG;
			Log.i("addEndRoute", "=" + e.getMessage());
		} finally {
			closeResources(null, db);
		}

		// return row id if any error it will return -1
		return ret;
	}

	// Get end route list by created date
	public ArrayList<EndRoute> getEndRouteList(long createdDate, Context context) {

		Cursor cursor = null;
		EndRoute address = null;
		SQLiteDatabase db = getReadableDataBase(context);
		ArrayList<EndRoute> arrayList = new ArrayList<EndRoute>();
		try {
			// Select All Query
			String selectQuery = "SELECT * FROM " + TABLE_END_ROUTE + " WHERE "
					+ COLUMN_CREATED_DATE + " = '" + createdDate + "'";
			Log.i("getEndRouteList", "=" + selectQuery);
			cursor = db.rawQuery(selectQuery, null);
			if (cursor.moveToFirst()) {
				do {
					address = new EndRoute();
					address.setAddress(cursor.getString(0));
					address.setLat(cursor.getDouble(1));
					address.setLng(cursor.getDouble(2));
					address.setCreatedDate(cursor.getLong(3));
					address.setIs_completed(cursor.getInt(4) == 0 ? false: true);
					//address.setNoteId(cursor.getInt(5));
					address.setUpdatedDate(cursor.getLong(6));
					address.setRowId(cursor.getInt(7));
					arrayList.add(address);
				} while (cursor.moveToNext());
			}

		} catch (SQLiteException e) {
			Log.i("getEndRouteList", "=" + e.getMessage());
		} catch (Exception e) {
			Log.i("getEndRouteList", "=" + e.getMessage());
		} finally {
			closeResources(cursor, db);
		}
		// return contact list
		return arrayList;

	}

	// Get all end route list
	public ArrayList<EndRoute> getAllEndRouteList(Context context) {

		Cursor cursor = null;
		EndRoute address = null;
		SQLiteDatabase db = getReadableDataBase(context);
		ArrayList<EndRoute> arrayList = new ArrayList<EndRoute>();
		try {
			// Select All Query
			String selectQuery = "SELECT * FROM " + TABLE_END_ROUTE;
			Log.i("getAllEndRouteList", "=" + selectQuery);
			cursor = db.rawQuery(selectQuery, null);
			Log.i("getAllEndRouteList cursor", "=" + cursor.getCount());
			if (cursor.moveToFirst()) {
				do {
					address = new EndRoute();
					
					//Get number of nodes from nodes table
					int notes = getNotesRowCount(cursor.getInt(7), LISTING,context);
					Log.i("image count", "=="+notes);
					address.setImgCount(notes);
					
					//Get start address from start route table
					String startAdress = getStartAdress(cursor.getInt(8), context);
					address.setStartAddress(startAdress);
					
					address.setAddress(cursor.getString(0));
					address.setLat(cursor.getDouble(1));
					address.setLng(cursor.getDouble(2));
					address.setCreatedDate(cursor.getLong(3));
					address.setIs_completed(cursor.getInt(4) == 0 ? false: true);
					//address.setNoteId(cursor.getInt(5));
					address.setUpdatedDate(cursor.getLong(6));
					address.setRowId(cursor.getInt(7));
					address.setRowIdOfStartRoute(8);
					address.setDistance("KM Away");
					arrayList.add(address);
				} while (cursor.moveToNext());
			}

		} catch (SQLiteException e) {
			Log.i("getAllEndRouteList", "=" + e.getMessage());
		} catch (Exception e) {
			Log.i("getAllEndRouteList", "=" + e.getMessage());
		} finally {
			closeResources(cursor, db);
		}
		// return contact list
		return arrayList;

	}
	


	// Adding new note
	public long addNotes(Notes address, Context context) {
		SQLiteDatabase db = getWritableDatabase(context);
		Log.i("addNotes", "addNotes");
		ContentValues values;

		long ret = QUARY_RESPONSE_LONG;
		try {
			values = new ContentValues();
			values.put(COLUMN_NOTE_NAME, address.getAddress());
			values.put(COLUMN_CREATED_DATE, address.getCreatedDate());
			values.put(COLUMN_UPDATE_DATE, address.getUpdatedDate());
			values.put(COLUMN_IMAGE_URL, address.getImageUrl());
			values.put(COLUMN_NOTES_OF, address.getNotesOf());
			values.put(COLUMN_NOTES_OF_ID, address.getNotesOfId());
			values.put(COLUMN_CAPTION, address.getCaption());
			values.put(COLUMN_IMG_COUNT, address.getImgCount());
			values.put(COLUMN_NOTE_TEXT, address.getNoteText());
			values.put(COLUMN_IS_IMG, address.isImg());
			ret = db.insert(TABLE_NOTE, null, values);

			/*
			 * if (isUpdateNeed(db, TABLE_ANIMAL_DETAILS, AnimalNumber,
			 * ""+animal.getAnimalNumber())) { Log.i("isUpdateNeed", "if");
			 * update(db, TABLE_ANIMAL_DETAILS, AnimalNumber,
			 * ""+animal.getAnimalNumber(), values); }else {
			 * Log.i("isUpdateNeed", "else"); // Inserting Row ret =
			 * db.insert(TABLE_ANIMAL_DETAILS, null, values); }
			 */

		} catch (SQLiteException e) {
			ret = QUARY_RESPONSE_LONG;
			Log.i("addNotes", "=" + e.getMessage());
		} catch (Exception e) {
			ret = QUARY_RESPONSE_LONG;
			Log.i("addNotes", "=" + e.getMessage());
		} finally {
			closeResources(null, db);
		}

		// return row id if any error it will return -1
		return ret;
	}

	// Get notes by id
	public int getNotesRowCount(int id, String type, Context context) {

		Cursor cursor = null;
		int row = 0;
		SQLiteDatabase db = getReadableDataBase(context);

		try {
			// Select All Query
			String selectQuery = "SELECT * FROM " + TABLE_NOTE + " WHERE "+ COLUMN_NOTES_OF_ID+ " = '"+id+"' AND "+ COLUMN_NOTES_OF+ " = '"+type+"'";
			Log.i("getNotes", "=" + selectQuery);
			cursor = db.rawQuery(selectQuery, null);
			row = cursor.getCount();
			Log.i("rowsssssssss", "=="+row);

		} catch (SQLiteException e) {
			Log.i("getNotes", "=" + e.getMessage());
		} catch (Exception e) {
			Log.i("getNotes", "=" + e.getMessage());
		} finally {
			closeResources(cursor, db);
		}
		// return contact list
		return row;

	}

	// Get notes by id
	public Notes getNotesById(int id, Context context) {

		Cursor cursor = null;
		Notes address = null;
		SQLiteDatabase db = getReadableDataBase(context);

		try {
			// Select All Query
			String selectQuery = "SELECT * FROM " + TABLE_NOTE + " WHERE "
					+ COLUMN_NOTE_ID + " = '" + id + "'";
			Log.i("getNotes", "=" + selectQuery);
			cursor = db.rawQuery(selectQuery, null);

			if (cursor.moveToFirst()) {

				address = new Notes();
				address.setAddress(cursor.getString(1));
				address.setImageUrl(cursor.getString(2));
				address.setCreatedDate(cursor.getLong(3));
				address.setUpdatedDate(cursor.getLong(4));
				address.setNotesOf(cursor.getString(5));
				address.setNotesOfId(cursor.getInt(6));
				address.setCaption(cursor.getString(7));
				address.setImgCount(cursor.getInt(8));
				address.setNoteText(cursor.getString(9));
				address.setIsImg(cursor.getInt(10));

			}

		} catch (SQLiteException e) {
			Log.i("getNotes", "=" + e.getMessage());
		} catch (Exception e) {
			Log.i("getNotes", "=" + e.getMessage());
		} finally {
			closeResources(cursor, db);
		}
		// return contact list
		return address;

	}

	// Get all notes list
	public ArrayList<Notes> getAllNotes(int id,Context context, String type) {

		Cursor cursor = null;
		Notes address = null;
		SQLiteDatabase db = getReadableDataBase(context);
		ArrayList<Notes> arrayList = new ArrayList<Notes>();
		try {
			String selectQuery ="SELECT * FROM " + TABLE_NOTE + " WHERE "+ COLUMN_NOTES_OF_ID+ " = '"+id+"' AND "+ COLUMN_NOTES_OF+ " = '"+type+"'";
			//String selectQuery ="SELECT * FROM " + TABLE_NOTE;
			/*if(isListing != null)
				selectQuery = "SELECT * FROM " + TABLE_NOTE + " WHERE "+ COLUMN_NOTES_OF_ID + " = '" + id + "'  AND "+COLUMN_NOTES_OF+"='"+(isListing?NOTE_OF_LISTING:NOTE_OF_FAV)+"'";
			else
				selectQuery = "SELECT * FROM " + TABLE_NOTE + " WHERE "+ COLUMN_NOTES_OF_ID + " = '" + id + "'";*/
			// Select All Query
			//String selectQuery = "SELECT * FROM " + TABLE_NOTE + " WHERE "
			Log.i("getAllNotes", "=" + selectQuery);
			cursor = db.rawQuery(selectQuery, null);
			Log.i("getAllNotes count", "=" + cursor.getCount());
			if (cursor.moveToFirst()) {
				do {
					address = new Notes();
					address.setNoteId(cursor.getInt(0));
					address.setAddress(cursor.getString(1));
					address.setImageUrl(cursor.getString(2));
					address.setCreatedDate(cursor.getLong(3));
					address.setUpdatedDate(cursor.getLong(4));
					address.setNotesOf(cursor.getString(5));
					address.setNotesOfId(cursor.getInt(6));
					address.setCaption(cursor.getString(7));
					address.setImgCount(cursor.getInt(8));
					address.setNoteText(cursor.getString(9));
					address.setIsImg(cursor.getInt(10));
					Log.d("get note", address.toString());
					arrayList.add(address);
				} while (cursor.moveToNext());
			}

		} catch (SQLiteException e) {
			Log.i("getAllNotes", "=" + e.getMessage());
		} catch (Exception e) {
			Log.i("getAllNotes", "=" + e.getMessage());
		} finally {
			closeResources(cursor, db);
		}
		// return contact list
		return arrayList;

	}

	public ArrayList<Notes> getDistinctNotes(Context context){

		Cursor cursor = null;
		Notes address = null;
		SQLiteDatabase db = getReadableDataBase(context);
		ArrayList<Notes> arrayList1 = new ArrayList<Notes>();
		ArrayList<Notes> arrayList2 = new ArrayList<Notes>();

		ArrayList<String> urlArray; 
		ArrayList<String> captionArray;
		/*String[] urlArray = null;
		String[] captionArray = null;*/

		try {
			// Select All Query  SELECT DISTINCT notes_of_id, notes_of  FROM note
			//String selectQuery = "SELECT DISTINCT notes_of_id FROM " + TABLE_NOTE;
			String selectQuery = "SELECT DISTINCT notes_of_id, notes_of FROM " + TABLE_NOTE;
			Log.i("getDistinctNotes", "=" + selectQuery);
			cursor = db.rawQuery(selectQuery, null);
			Log.i("getDistinctNotes", "=" + cursor.getCount());
			if (cursor.moveToFirst()) {
				do {
					address = new Notes();
					arrayList1 = getAllNotes(cursor.getInt(0), context, cursor.getString(1));
					String title = getFavouritesTitle(cursor.getInt(0), context);
					/*arrayList1 = getAllNotes(cursor.getInt(0), context, cursor.getString(1));
					Log.i("testing", "==="+arrayList1.size());
					urlArray = new ArrayList<String>();
					captionArray = new ArrayList<String>();
					for (int i = 0; i < arrayList1.size(); i++) {
						
						
						urlArray.add(arrayList1.get(i).getImageUrl());
						captionArray.add(arrayList1.get(i).getCaption());
					}

					address.setAddress(arrayList1.get(0).getAddress());
					address.setUrlArray(urlArray);
					address.setCaptionArray(captionArray);*/
					
					address.setAddress(title);
					address.setNotesOf(cursor.getString(1));
					address.setNotesOfId(cursor.getInt(0));

					address.setNotesArray(arrayList1);
					arrayList2.add(address);
				} while (cursor.moveToNext());
			}

		} catch (SQLiteException e) {
			Log.i("getAllNotes", "=" + e.getMessage());
		} catch (Exception e) {
			Log.i("getAllNotes", "=" + e.getMessage());
		} finally {
			closeResources(cursor, db);
		}
		return arrayList2;
	}

	// Update notes by note id
	public int updateNotes(Notes address, Context context) {
		Log.i("updateNotes", "updateNotes");
		SQLiteDatabase db = getWritableDatabase(context);
		int ret = QUARY_RESPONSE_INT;
		try {

			ContentValues values = new ContentValues();
			values.put(COLUMN_NOTE_NAME, address.getAddress());
			values.put(COLUMN_CREATED_DATE, address.getCreatedDate());
			values.put(COLUMN_UPDATE_DATE, address.getUpdatedDate());
			values.put(COLUMN_IMAGE_URL, address.getImageUrl());
			values.put(COLUMN_NOTES_OF, address.getNotesOf());
			values.put(COLUMN_NOTES_OF_ID, address.getNotesOfId());
			values.put(COLUMN_CAPTION, address.getCaption());
			values.put(COLUMN_IMG_COUNT, address.getImgCount());

			ret = db.update(TABLE_NOTE, values, COLUMN_NOTE_ID + " = ?",
					new String[] { String.valueOf(address.getNoteId()) });

		} catch (SQLiteException e) {
			ret = QUARY_RESPONSE_INT;
			Log.i("updateNotes", "=" + e.getMessage());

		} catch (Exception e) {
			ret = QUARY_RESPONSE_INT;
			Log.i("updateNotes", "=" + e.getMessage());

		} finally {
			closeResources(null, db);
		}
		return ret;
	}

	// Delete notes by note id
	public int deleteNotes(int noteID, Context contact) {

		SQLiteDatabase db = getWritableDatabase(contact);
		int ret = 0;
		try {

			ret = db.delete(TABLE_NOTE, COLUMN_NOTES_OF_ID + " = ?",
					new String[] { String.valueOf(noteID) });

		} catch (SQLiteException e) {
			ret = QUARY_RESPONSE_INT;
			Log.i("updateNotes", "=" + e.getMessage());

		} catch (Exception e) {
			ret = QUARY_RESPONSE_INT;
			Log.i("updateNotes", "=" + e.getMessage());

		} finally {
			closeResources(null, db);
		}
		// the number of rows affected if a whereClause is passed in, 0
		// otherwise. To remove all rows and get a count pass "1" as the
		// whereClause.
		// Any exception return -1
		return ret;
	}
	
	public void insertNotification(Context context,Notifications notifications){
		SQLiteDatabase db = getWritableDatabase(context);
		Log.i("insertAppDetails", "insertAppDetails");
		ContentValues values;

		try {
			if (notifications != null) {

				values = new ContentValues();

				values.put(COLUMN_USER_ID, notifications.getId());
				values.put(COLUMN_TYPE, notifications.getType());
				values.put(COLUMN_MESSAGE, notifications.getMessage());
				values.put(COLUMN_ICON, notifications.getIcon());
				values.put(COLUMN_TIME, notifications.getTime());

				db.insert(TABLE_NOTIFICATION, null, values);

			}


		} catch (SQLiteException e) {
			Log.i("insertAppDetails", "=" + e.getMessage());
		} catch (Exception e) {
			Log.i("insertAppDetails", "=" + e.getMessage());
		} finally {
			closeResources(null, db);
		}	

	}
}
