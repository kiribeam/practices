package com.kiri;

public class BinaryTools {
	public static String byteToHex(byte b){
		byte b2 = (byte)(b & 0xf);
		byte b1 = (byte) ((b & 0xf0) >> 4);
		return ""+Integer.toHexString(b1)+Integer.toHexString(b2);
	}
	
	//Show with space
	public static String bytesToHex(byte[] bs){
		StringBuilder sb = new StringBuilder("");
		for(byte b: bs){
			sb.append(" " + byteToHex(b));
		}
		sb.deleteCharAt(0);
		return sb.toString();
	}
	//To a total line.
	public static String bytesToHexLine(byte[] bs){
		StringBuilder sb = new StringBuilder("");
		for(byte b: bs)
			sb.append(byteToHex(b));
		return sb.toString();
	}
	
	public static void main(String[] args){
		System.out.println(byteToHex((byte) 0xf0));
	}

}
