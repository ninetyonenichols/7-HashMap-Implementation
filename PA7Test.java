
/*
 * AUTHOR: Justin Nichols
 * FILE: PA7Test.java
 * ASSIGNMENT: Programming Assignment 7 - HashMapImpl
 * COURSE: CSC210; Section D; Spring 2019
 * PURPOSE: Tests whether the MyHashMap class works
 *              
 * USAGE: 
 * java PA7Test 
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

public class PA7Test {
    /*
     * tests whether MyHashMap correctly puts a key in its keySet 
     */
    @Test
    public void testPutKey() {
        MyHashMap testMap = new MyHashMap<String, Integer>();
        testMap.put("testKey", 7);
        assertTrue(testMap.containsKey("testKey"));
    }

    /*
     * tests whether MyHashMap correctly stores a value corresponding to a key 
     */
    @Test
    public void testGeKey() {
        MyHashMap testMap = new MyHashMap<String, Integer>();
        testMap.put("testKey", 7);
        assertEquals(7, testMap.get("testKey"));
    }
    
    /*
     * tests whether MyHashMap correctly retrieves a value corresponding to a 
     * key
     */
    @Test
    public void testGet() {
        MyHashMap testMap = new MyHashMap<Integer, Boolean>();
        testMap.put(0, false);
        testMap.put(1, true);
        assertEquals(true, testMap.get(1));
    }

    /*
     * tests whether MyHashMap will correctly identify when it does not contain
     * a key
     */
    @Test
    public void testDoesntContainKey() {
        MyHashMap testMap = new MyHashMap<Double, List<Integer>>();
        Double testKey = 3.14;
        assertFalse(testMap.containsKey(testKey));
    }

    /*
     * tests whether MyHashMap will correctly identify when it does contain a 
     * key
     */
    @Test
    public void testDoesContainKey() {
        MyHashMap testMap = new MyHashMap<Double, List<Integer>>();
        Double testKey = 3.14;
        List<Integer> testVal = new ArrayList<Integer>(
                Arrays.asList(1, 2, 3, 4, 5));
        testMap.put(testKey, testVal);
        assertTrue(testMap.containsKey(testKey));
    }

    /*
     * tests whether MyHashMap builds up its keySet as it should
     */
    @Test
    public void testKeySet() {
        MyHashMap testMap = new MyHashMap<String, Integer>();
        testMap.put("Cervical", 7);
        testMap.put("Thoracic", 12);
        testMap.put("Lumbar", 5);

        Set<String> correctKeySet = new TreeSet<String>(
                Arrays.asList("Cervical", "Thoracic", "Lumbar"));

        assertEquals(testMap.keySet(), correctKeySet);
    }
}