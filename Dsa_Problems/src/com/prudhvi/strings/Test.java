package com.prudhvi.strings;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class Test {

	public static void main(String[] args) {
		String[] fruits = { "Banana","Apple", "Cherry","Apple", "Banana", "Cherry"};
		System.out.print(Arrays.toString(sett(fruits)));
		
	}
	public static String[] sett(String[] items) {
		LinkedHashSet<String> set = new LinkedHashSet<>();
		for(int i = 0; i < items.length; i++) {
			set.add(items[i]);
		}
		String[] eleStrings = new String[set.size()];
		int index = 0;
		for(String string : set) {
			eleStrings[index] = string;
			index++;
		}
		
		return eleStrings;
	}
}
