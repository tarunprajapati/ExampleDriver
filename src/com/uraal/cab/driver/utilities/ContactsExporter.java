package com.uraal.cab.driver.utilities;
/*package com.yft.backup2restore.exporter;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import com.yft.backup2restore.R;
import com.yft.backup2restore.Strings;


public class ContactsExporter extends SimpleExporter {
	public static final int ID = 8;
	
	public static final int NAMEID = R.string.contacts;
	
	public static final String NAME = Strings.CONTACTS;
	
	public static String LOOKUP_FIELDNAME;
	
	public static Uri CONTACTS_URI;
	
	public static Uri VCARD_URI;
	
	static {
		try {
			Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass("android.provider.ContactsContract$Contacts");

			LOOKUP_FIELDNAME = (String) clazz.getField("LOOKUP_KEY").get(null);
			CONTACTS_URI = (Uri) clazz.getDeclaredField("CONTENT_URI").get(null);
			VCARD_URI = (Uri) clazz.getDeclaredField("CONTENT_VCARD_URI").get(null);
		} catch (Exception e) {
			
		}
	}
	
	private int lookupKeyColumn = -1;
	
	public ContactsExporter(ExportTask exportTask) {
		super(Strings.TAG_CONTACT, new String[] {LOOKUP_FIELDNAME}, CONTACTS_URI, false, exportTask);
	}
	
	@Override
	public void addText(Cursor cursor, Writer writer) throws IOException {
		if (lookupKeyColumn == -1) {
			lookupKeyColumn = cursor.getColumnIndex(LOOKUP_FIELDNAME);
		}
		writer.append(TextUtils.htmlEncode(new String(getVcardBytes(context, cursor.getString(lookupKeyColumn)))));
	}
	
	@Override
	public boolean maybeIncomplete() {
		return true;
	}

	public static byte[] getVcardBytes(Context context, String lookupKey) throws IOException {
		Uri contactUri = Uri.withAppendedPath(VCARD_URI, lookupKey);
		
		AssetFileDescriptor assetFileDescriptor = context.getContentResolver().openAssetFileDescriptor(contactUri, "r");
		
		InputStream fileInputStream = assetFileDescriptor.createInputStream();
		
		byte[] buffer = new byte[(int) assetFileDescriptor.getDeclaredLength()];
		
		fileInputStream.read(buffer);
		fileInputStream.close();
		
		return buffer;
	}

}
*/