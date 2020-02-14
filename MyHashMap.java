
/**
 * AUTHOR: Justin Nichols
 * FILE: HashMap.java
 * ASSIGNMENT: Programming Assignment 7 - HashMapImpl
 * COURSE: CSC210; Section D; Spring 2019
 * PURPOSE: Implements the HashMap ADT
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MyHashMap<K, V> {
    private final int NUM_BUCKETS = 8;
    private Set<K> keySet = new TreeSet<K>();
    private List<LinkedList<HashNode>> values = 
    		new ArrayList<LinkedList<HashNode>>();
    
    /*
     * constructor for the MyHashMap class. Initializes 'values' to be an 
     * ArrayList which contains eight empty LinkedLists which will be used as
     * buckets
     */
    public MyHashMap() {
        for (int i = 0; i < NUM_BUCKETS; i++) {
            values.add(new LinkedList<HashNode>());
        }
    }

    /*
     * creates a hash-value for a given key
     * 
     * @param K key, the key in question
     * 
     * @return Int [unnamed], the corresponding hash-value
     */
    private int hash(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % NUM_BUCKETS;
        return Math.abs(index);
    }

    /*
     * adds a key to the keyset and a value corresponding to that key
     * at the head of the appropriate LinkedList (bucket)
     * 
     * @param K key, the key in question
     * 
     * @param V value, the value in question
     */
    public void put(K key, V value) {
        if (keySet.contains(key)) {
            HashNode node = findNode(key);
            node.setValue(value);
            return;
        }

        keySet.add(key);
        int index = hash(key);
        List<HashNode> bucket = values.get(index);
        HashNode oldHead = null;
        if (!bucket.isEmpty()) {
            oldHead = bucket.get(0);
        }

        HashNode newNode = new HashNode(key, value);
        newNode.setNext(oldHead);
        bucket.add(0, newNode);
    }

    /*
     * returns the value corresponding to 'key'
     */
    public V get(K key) {
        if (!keySet.contains(key)) {
            return null;
        }

        HashNode node = findNode(key);
        return (V) node.getValue();
    }

    /*
     * returns the node which stores 'key'. Assumes the existence of
     * such a node
     */
    public HashNode findNode(K key) {
        int index = hash(key);
        List<HashNode> bucket = values.get(index);
        HashNode curr = bucket.get(0);

        while (!curr.getKey().equals(key)) {
            curr = curr.getNext();
        }

        return curr;
    }

    /*
     * tells user whether keySet contains 'key'
     */
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    public Set<K> keySet() {
        return keySet;
    }

    /*
     * prints info about the hashTable. Used for debugging the PA7Main.java 
     * file 
     */
    public void printTable() {
        System.out.println("DEBUG output for MyTable");
        System.out.println("-------------------------------------");

        int totConfs = 0;
        for (int i = 0; i < NUM_BUCKETS; i++) {
            List<HashNode> bucket = values.get(i);
            int nConfs = bucket.size() > 1 ? bucket.size() - 1 : 0;

            System.out.printf("Index %d: (%d conflicts), [", i, nConfs);
            HashNode curr = bucket.isEmpty() ? null : bucket.get(0);
            while (curr != null) {
                System.out.printf("%s, ", curr.getKey().toString());
                curr = curr.getNext();
            }
            System.out.println("]");
            totConfs += nConfs;
        }
        System.out.printf("Total # of conflicts: %d", totConfs);
    }
}
