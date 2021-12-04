public class Node<T> {
    private T item;
    private Node<T> next;

    public Node(T obj) {
        this.item = obj;
        this.next = null;
    }
    
    public Node(T obj, Node<T> next) {
    	this.item = obj;
    	this.next = next;
    }
    
    public final T getItem() {
    	return item;
    }
    
    public final void setItem(T item) {
    	this.item = item;
    }
    
    public final void setNext(Node<T> next) {
    	this.next = next;
    }
    
    public Node<T> getNext() {
    	return this.next;
    }
    
    public final void insertNext(T obj) {
        Node<T> newNode = new Node<>(obj, getNext());
        setNext(newNode);
    }
    
    public final void removeNext() {
        // TODO : Is it needed to check whether it has next element? (0416, 18:41)
        // It looks pretty redundant, cuz this method is called only in Iterator.
        // And it is already checking whether it has next one.
        if (getNext() != null) {
            setNext(getNext().getNext());
        }
    }
}