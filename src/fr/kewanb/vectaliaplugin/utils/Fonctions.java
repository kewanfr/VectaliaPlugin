package fr.kewanb.vectaliaplugin.utils;

public class Fonctions {
	public static String upperCaseFirst(String val) {
		char[] arr = val.toCharArray();
		arr[0] = Character.toUpperCase(arr[0]);
		return new String(arr);
	}
}
