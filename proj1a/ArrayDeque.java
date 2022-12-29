public class ArrayDeque <T> {
    private int size;
    private int first;
    private int last;
    private T[] items;
    private int capacity=8;
    public ArrayDeque(){
        items = (T[]) new Object[capacity];
        first = last = 0;
    }
    public void addFirst(T item){
        if(isFull()){
            resize(2*capacity);
        }
        first = (first-1+capacity)%capacity;
        items[first] = item;
        size+=1;
    }
    public void addLast(T item){
        if(isFull()){
            resize(2*capacity);
        }
        items[last] = item;
        last=(last+1+capacity)%capacity;
        size+=1; 
    }
    public boolean isEmpty(){
        return first==last;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        for(int i=0;i<size-1;i++){
            int j = (i+first) % capacity;
            if(items[j] ==null){
                continue;
            }
            System.out.print(items[j] + " ");
        }
        System.out.println(items[last]);
    }
    public T removeFirst(){
        if(size==0) return null;
        T itemToReturn = items[first];
        first =(first+1+capacity)%capacity;
        size-=1;
        return itemToReturn;
    }
    public T removeLast(){
        if(size==0) return null;
        T itemToReturn = items[last];
        last =(last+1+capacity)%capacity;
        size-=1;
        if(arrayIsLow()){
            resize(capacity/2);
        }
        return itemToReturn;
    }
    public T get(int index){
        if(index>=size){
            return null;
        }
        int numToReturn;
        numToReturn = (first + index)%capacity;
        return items[numToReturn];
    }
    private boolean isFull(){
        return size==capacity;
    }
    private boolean arrayIsLow(){
        return size>=16 && (size/capacity)<0.25;
    }

    private void resize(int newSize){
        T[] newArray = (T[]) new Object[newSize];
        //分成两种情况
        if(first<last){   //十分正常的平移操作
            for(int i=0;i<size;i++){
                newArray[i] = items[first+i];
            }
        }
        else{  //理论上只有first>last
            for(int i=0;i<size;i++){
                int j = (first+i)%capacity;
                newArray[i] = items[j];
                }
            }
        capacity = newSize;
        first = 0;
        last = size - 1;
        items = newArray;
        }
}
