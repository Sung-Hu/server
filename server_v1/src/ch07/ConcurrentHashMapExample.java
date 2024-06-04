package ch07;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
	public static void main(String[] args) {
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
		
		// 키-값-쌍 추가
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);
		
		// 값 가져오기
		System.out.println("Value for 'one' : " + map.get("one"));
		System.out.println("Value for 'one' : " + map.get("two"));
		
		// 키 확인
		System.out.println("Map contains key 'three' : " + map.containsKey("three"));
		// 값 제거
		map.remove("two");
		System.out.println("Map contains key 'two' after removal : " + map.containsKey("two"));
		
		// 모든 키 출력
		for(String key : map.keySet()) {
			System.out.println("Key : " + key + ", Value : " + map.get(key));
		}
	}// end of main
}// end of class
