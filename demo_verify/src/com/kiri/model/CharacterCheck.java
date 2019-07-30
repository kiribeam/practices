package com.kiri.model;
//no use
public class CharacterCheck {
	public static boolean checkString(String s, boolean num, boolean character, boolean at){
		for(int i=0; i<s.length(); i++){
		}
		return true;
	}

	public static boolean checkNum(String s){
		for(int i=0; i<s.length(); i++){
			if(!Character.isDigit(s.charAt(i))) return false;
		}
		return true;
	}

	public static boolean checkChar(String s){
		for(int i=0; i<s.length(); i++){
			if(!Character.isDigit(s.charAt(i))) return false;
		}
		return true;
	}
	public static boolean checkAt(String s){
		return true;
	}
}
