public class LinkedList<T> implements ListInterface<T>{
    private Node<T> head;
    private int numItems;

    public LinkedList() {
       numItems = 0;
       head = new Node<T>();
       head.setNext(null);
    }

    public boolean isEmpty() {
       return true;
    }
    public int size() {
       return 0;
    }

    public void add(int index, T item) {

    }

    public void remove(int index) {

    }
    public T get(int index) {

    }
    public void removeAll() {

    }
}
