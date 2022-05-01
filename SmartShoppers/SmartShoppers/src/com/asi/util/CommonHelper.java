package com.asi.util;

import org.apache.commons.lang.ArrayUtils;

public class CommonHelper {
	
	public static char[] toCharacterArray( String str ) {

		   if ( str == null ) {
		     return null;
		   }

		   char[] charArray = str.toCharArray();
		   Character[] charObjectArray = ArrayUtils.toObject(charArray);
		   return charArray;
		}

}
