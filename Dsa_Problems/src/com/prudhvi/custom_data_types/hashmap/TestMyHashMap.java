package com.prudhvi.custom_data_types.hashmap;

public class TestMyHashMap {

	public static void main(String[] args) {
		MyHashMap<Integer, Integer> hashMap=new MyHashMap<>();
		for(int i=1;i<=100;i++) {
			hashMap.put(i,i);
		}
		System.out.println(hashMap.containsKey(1001));
	}

}
