package lab9;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root; /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the value mapped to by KEY in the subtree rooted in P.
     * or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if(key==null||p==null) return null;
        if (key.compareTo(p.key) == 0) {
            return p.value;
        } else {
            if (key.compareTo(p.key)>0) {
                return getHelper(key, p.right);
            } else {
                return getHelper(key, p.left);
            }
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /**
     * Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if(p==null){
            size+=1;
            p = new Node(key, value);
            return p;
        }
        if(key.compareTo(p.key)==0) return null;
        if (key.compareTo(p.key)>0) {
            p.right = putHelper(key, value, p.right);
            return p;
        } else {
            p.left = putHelper(key, value, p.left);
            return p;
        }
    }

    /**
     * Inserts the key KEY
     * If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root=putHelper(key, value, root);
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
        Set<K> set1 = new TreeSet<>();
        keySetHelpMethod(set1, root);
        return set1;
    }

    private void keySetHelpMethod(Set<K> set1,Node p){
        if(p.key==null) return;
        set1.add(p.key);
        keySetHelpMethod(set1, p.left);
        keySetHelpMethod(set1, p.right);
    }
    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        V value = get(key);
        root = remove(key,root);
        return value;
    }
    public Node remove(K key,Node node){
        if (key.compareTo(node.key)>0) {
            node.right = remove(key,node.right);
            return node;
        } else {
            if(key.compareTo(node.key)<0){
                node.left = remove(key, node.left);
                return node;
            }
        }
        if(node.left==node.right&&node.right==null){
            size-=1;
            return null;
        }
        else{
            if(node.left==null&&node.right!=null){
            node = node.right;
            size-=1;
            return node;
            }
            else{
                if(node.right==null&&node.left!=null){
                    node = node.left;
                    size-=1;
                    return node;
                    }
                    else{  //此时要删除的节点两边都有子树，需要找到左子树最大节点
                        Node nodeToMove = findBigOne(node.left);
                        node.value = nodeToMove.value;
                        node.key = nodeToMove.key;
                        size-=1;
                        return nodeToMove;
                    }
            }
        }
    }
    private Node findBigOne(Node node){
        if(node.right!=null){
            node.right = findBigOne(node.right);
            return node.right;
        }else{
            return node;
        }
    }
    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value. Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V valueInMap = get(key);
        if(value.equals(valueInMap)){
            remove(key);
            return value;
        }else{
            return null;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTIterator();
    }
    private class BSTIterator implements Iterator<K>{
        private Iterator<K> cur;
        public BSTIterator(){
            cur=keySet().iterator();
        }
        @Override
        public boolean hasNext() {
            return cur.hasNext();
        }

        @Override
        public K next() {
            return cur.next();
        }
    }
}
