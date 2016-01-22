package com.uraal.cab.driver.utilities;

public class IsImageURL
{
	public static boolean isImageURL(String photo)
	{
		try 
		{
			if(photo != null)
			{
				String arr_slash[] = photo.split("/");
				if(arr_slash != null && arr_slash.length > 0)
				{
					String dot = arr_slash[arr_slash.length-1];
					if(dot != null && dot.contains("."))
					{
						return true;
					}
					return false;
				}
				return false;
			}
			return false;
		} 
		catch (Exception e) 
		{		
			e.printStackTrace();
			return false;
		}
	}
}
