	package com.uraal.cab.driver.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.uraal.cab.driver.R;


public class AlertDialogClass {
	public static void alertWhileBack(final Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		AlertDialog dialog = builder.create();
		dialog.setTitle(context.getString(R.string.warning));
		dialog.setMessage("Are you sure?");
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();	
			}
		});

		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Back", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				((Activity)context).finish();	
			}
		});

		dialog.show();
	}


	/*public static void alertDelete(final Context context, final ListAdapter adapter){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		AlertDialog dialog = builder.create();
		dialog.setTitle(context.getString(R.string.warning));
		dialog.setMessage(context.getString(R.string.warning_sure_selected_files));
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Back", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();	
			}
		});

		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Delete Selected", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				ArrayList<HashMap<String, String>> list = adapter.getList();
				for(int item=0; item < list.size(); item++){
					if(list.get(item).get(Constants.isCheck) != null && list.get(item).get(Constants.isCheck).equalsIgnoreCase("true"))
						list.remove(item);
				}

				adapter.setList(list);
			}
		});

		dialog.show();
	}*/

}
