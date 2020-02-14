
/**
 * AUTHOR: Justin Nichols
 * FILE: HashNode.java
 * ASSIGNMENT: Programming Assignment 7 - HashMapImpl
 * COURSE: CSC210; Section D; Spring 2019
 * PURPOSE: Implements a HashNode which will store key, value pairs.
 *          This will in turn help with implementing the HashMap ADT.
 */

public class HashNode<K, V> {
    private K key;
    private V value;
    private HashNode<K, V> next;

    /*
     * constructor for the HashNode class
     * 
     * @param K key
     * 
     * @param V value, corresponds to key.
     */
    public HashNode(K key, V value) {
        this.setKey(key);
        this.setValue(value);
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public HashNode<K, V> getNext() {
        return next;
    }

    public void setNext(HashNode<K, V> next) {
        this.next = next;
    }
}