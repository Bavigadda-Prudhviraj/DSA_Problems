package com.prudhvi.custom_data_types.hashmap;

public class TestMyHashMap {

	public static void main(String[] args) {
		
		MyHashMap<Integer, Integer> hashMap=new MyHashMap<>();
		hashMap.put(1,1);
		hashMap.put(2,2);
		System.out.println(hashMap.get(1)); //1
		System.out.println(hashMap.get(3));//we will get null
		hashMap.put(2,1);
		hashMap.remove(2);
		System.out.println(hashMap.get(2)); //we will get null
		
		
	}

}
