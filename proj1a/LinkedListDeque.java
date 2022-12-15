public class LinkedListDeque <T>{
    public int size;
    public LinkedList <T> sentinel;
    public class LinkedList <Item>{
        Item first;
        LinkedList <Item> next;
        public LinkedList(Item item1,LinkedList next1){
            first = item1;
            next = next1; 
        }
    }
    public LinkedListDeque(T item){
        sentinel = new LinkedList<T>(item,null);
    }

    public void addFirst(T item){ //add remove 操作使用双指针

    }
    public void addlast(T item){

    }
    public boolean isEmpty(){           
        return  sentinel==sentinel.next;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        
    }
    public T removeFirst(){
        return null;
    }
    public T removeLast(){
        return null;
    }
    public T get(int index){  //get使用迭代而非递归
        return null;
    }
    public T getRecursive(int index){
        
        return null;
    }
}
