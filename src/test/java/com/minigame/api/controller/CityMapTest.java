package com.minigame.api.controller;

import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class CityMapTest {

	@Test
	public void test1() {
		Map<String, Integer> cityNames = new ConcurrentHashMap<String, Integer>();
		cityNames.put("City1", 0);
		cityNames.put("City2", 10);
		cityNames.put("City3", 25);
		Map<String, Integer> result = updateCityNames(cityNames, 30);
		assertTrue(result.get("City1") == 0);
		assertTrue(result.get("City2") == 10);
		assertTrue(result.get("City3") == 20);
	}

	@Test
	public void test2() {
		Map<String, Integer> cityNames = new ConcurrentHashMap<String, Integer>();
		cityNames.put("City1", 20);
		cityNames.put("City2", 20);
		cityNames.put("City3", 30);
		Map<String, Integer> result = updateCityNames(cityNames, 30);
		assertTrue(result.get("City1") == 10);
		assertTrue(result.get("City2") == 10);
		assertTrue(result.get("City3") == 10);
	}
	
	@Test
	public void test3() {
		Map<String, Integer> cityNames = new ConcurrentHashMap<String, Integer>();
		cityNames.put("City1", 3);
		cityNames.put("City2", 3);
		cityNames.put("City3", 5);
		Map<String, Integer> result = updateCityNames(cityNames, 30);
		assertTrue(result.get("City1") == 3);
		assertTrue(result.get("City2") == 3);
		assertTrue(result.get("City3") == 5);
	}
	
	@Test
	public void test4() {
		Map<String, Integer> cityNames = new ConcurrentHashMap<String, Integer>();
		cityNames.put("City1", 0);
		cityNames.put("City2", 0);
		cityNames.put("City3", 50);
		Map<String, Integer> result = updateCityNames(cityNames, 30);
		assertTrue(result.get("City1") == 0);
		assertTrue(result.get("City2") == 0);
		assertTrue(result.get("City3") == 30);
	}
	
	@Test
	public void test5() {
		Map<String, Integer> cityNames = new ConcurrentHashMap<String, Integer>();
		cityNames.put("City1", 0);
		cityNames.put("City2", 20);
		cityNames.put("City3", 30);
		Map<String, Integer> result = updateCityNames(cityNames, 30);
		assertTrue(result.get("City1") == 0);
		assertTrue(result.get("City2") == 15);
		assertTrue(result.get("City3") == 15);
	}
	
	@Test
	public void test6() {
		Map<String, Integer> cityNames = new ConcurrentHashMap<String, Integer>();
		cityNames.put("City1", 50);
		cityNames.put("City2", 0);
		cityNames.put("City3", 0);
		Map<String, Integer> result = updateCityNames(cityNames, 30);
		assertTrue(result.get("City1") == 30);
		assertTrue(result.get("City2") == 0);
		assertTrue(result.get("City3") == 0);
	}
	
	@Test
	public void test7() {
		Map<String, Integer> cityNames = new ConcurrentHashMap<String, Integer>();
		cityNames.put("City1", 1);
		cityNames.put("City2", 100);
		cityNames.put("City3", 8);
		cityNames.put("City4", 7);
		cityNames.put("City5", 6);
		Map<String, Integer> result = updateCityNames(cityNames, 50);
		assertTrue(result.get("City1") == 1);
		assertTrue(result.get("City2") == 28);
		assertTrue(result.get("City3") == 8);
		assertTrue(result.get("City4") == 7);
		assertTrue(result.get("City5") == 6);
	}
	
	@Test
	public void test8() {
		Map<String, Integer> cityNames = new ConcurrentHashMap<String, Integer>();
		cityNames.put("City1", 1);
		cityNames.put("City2", 50);
		cityNames.put("City3", 50);
		cityNames.put("City4", 2);
		cityNames.put("City5", 7);
		Map<String, Integer> result = updateCityNames(cityNames, 50);
		assertTrue(result.get("City1") == 1);
		assertTrue(result.get("City2") == 20);
		assertTrue(result.get("City3") == 20);
		assertTrue(result.get("City4") == 2);
		assertTrue(result.get("City5") == 7);
	}
	
	@Test
	public void test9() {
		Map<String, Integer> cityNames = new ConcurrentHashMap<String, Integer>();
		cityNames.put("City1", 2);
		cityNames.put("City2", 50);
		cityNames.put("City3", 50);
		cityNames.put("City4", 50);
		cityNames.put("City5", 18);
		Map<String, Integer> result = updateCityNames(cityNames, 50);
		assertTrue(result.get("City1") == 2);
		assertTrue(result.get("City2") == 12);
		assertTrue(result.get("City3") == 12);
		assertTrue(result.get("City4") == 12);
		assertTrue(result.get("City5") == 12);
	}
	
	private Map<String, Integer> updateCityNames(
			Map<String, Integer> cityNames, int total) {
		int sum = 0;
		for (String city : cityNames.keySet()) {
			sum += cityNames.get(city);
		}
		int average = total / cityNames.keySet().size();
		Map<String, Integer> rest = new ConcurrentHashMap<String, Integer>();
		if (sum > total) {
			for (String city : cityNames.keySet()) {
				int cityValue = cityNames.get(city);
				if (cityValue <= average) {
					total -= cityValue;
				} else {
					cityNames.put(city, average);
					rest.put(city, cityValue - average);
					total -= average;
				}
			}
		}
		if (total > 0 && !rest.isEmpty()) {
			while (total > 0 && !rest.isEmpty()) {
				for (String city : rest.keySet()) {
					int newCityValue = cityNames.get(city) + 1;
					int newRestValue = rest.get(city) - 1;
					cityNames.put(city, newCityValue);
					rest.put(city, newRestValue);
					total--;
					if (newRestValue == 0) {
						rest.remove(city);
					} 
				}
			}
		}
		return cityNames;
	}

}
