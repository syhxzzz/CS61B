public class LinkedListDeque <T>{
    private int size;
    private Node  sentinel;
    public class Node {
        T item;
        Node  next;
        Node prev;
        public Node(T item,Node prev,Node next){
            this.item = item;
            this.next = next; 
            this.prev = prev;
        }
    }
    public LinkedListDeque(){
        sentinel = new Node ((T)new Object() ,null,null);
        sentinel.prev=sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item){ //add remove 操作使用双指针
        Node p1 = sentinel.next;
        Node p = new Node(item,null,null);
        sentinel.next = p;
        p.prev = sentinel;
        p1.prev = p;
        p.next = p1;
        size+=1;
    }
    public void addLast(T item){
        Node last1 = sentinel.prev;
        Node newlast1 = new Node(item,null,null);
        last1.next = newlast1;
        newlast1.prev=last1;
        newlast1.next = sentinel;
        sentinel.prev = newlast1;
        size+=1;
    }
    public boolean isEmpty(){           
        return  sentinel==sentinel.next;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        Node ptr = sentinel.next;
        while(ptr.next != sentinel){
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println(ptr.item);
    }
    public T removeFirst(){
        Node first = sentinel.next;
        Node second = first.next;
        first.next=first.prev=null;
        second.prev = sentinel;
        sentinel.next = second;
        size-=1;
        return first.item;
    }
    public T removeLast(){
        Node last1 = sentinel.prev;
        Node last2 = last1.prev;
        last1.prev = last1.next = null;
        last2.next = sentinel;
        sentinel.prev = last2;
        size-=1;
        return last1.item;
    }
    public T get(int index){  //get使用迭代而非递归
        if(index>=size){
            return null;
        }
        Node ptr = sentinel;
        for(int i=0;i<index;i++){
            ptr = ptr.next;
        }
        return ptr.item;
    }
    public T getRecursive(int index){

        return getRecursiveNode(index, sentinel.next);
    }
    private T getRecursiveNode(int index , Node p){
        if(index==0){
            return p.item;
        }
        return getRecursiveNode(index-1, p.next);
    }
}
