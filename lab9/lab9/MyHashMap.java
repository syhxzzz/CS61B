package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;
    private int capacity = DEFAULT_SIZE;
    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / capacity;
    }

    public MyHashMap() {
        buckets = new ArrayMap[capacity];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = capacity;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int num = hash(key);
        return buckets[num].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        int num = hash(key);
        if(!containsKey(key))   size+=1;
        buckets[num].put(key, value);
        if(loadFactor()>MAX_LF){
            resize();
        }
    }
    private void resize(){
        capacity = 2*capacity;
            ArrayMap<K, V>[] oldBuckets = buckets;
            buckets=(ArrayMap<K,V>[])new ArrayMap[capacity];
            this.clear();
            for(int i=0;i<oldBuckets.length;i+=1){
                Set<K>set=oldBuckets[i].keySet();
            Iterator<K>it=set.iterator();
                while (it.hasNext()){
                    K next = it.next();
                    put(next,oldBuckets[i].get(next));
                }
            }
    }
    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set <K> keySet = new HashSet<>();
        for(ArrayMap<K,V> bucket:buckets){
            keySet.addAll(bucket.keySet());
        }
        return keySet;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        int hashCode = hash(key);
        if(buckets[hashCode].containsKey(key)){
            size-=1;
            return buckets[hashCode].remove(key);
        }else{
            return null;
        }
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        int hashCode = hash(key);
        if(buckets[hashCode].containsKey(key)&&buckets[hashCode].get(key)==value){
            size-=1;
            return buckets[hashCode].remove(key);
        }else{
            return null;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
