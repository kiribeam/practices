package com.kiri;

import java.util.Arrays;
import java.util.Scanner;

public class IOTools {
	public static Scanner sc;
	public static int[] readIntArrayLine(){
		sc = new Scanner(System.in);
		String line = sc.nextLine();
		sc.close();
		String[] ints = line.split("\\s+");
		int length = ints.length;
		int[] array = new int[length];
		for(int i=0; i<length; i++)
			array[i] = Integer.parseInt(ints[i]);
		return array;
	}
	public static void main(String[] args){
		System.out.println(Arrays.toString(readIntArrayLine()));
	}

}
