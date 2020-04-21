package dataStructureHW2;

public class Node<T> {
    private T item;
    private Node<T> next;
    private Node<T> prev;

    public Node(T obj) {
        this.item = obj;
        this.next = null;
        this.prev = null;
    }
    
    public Node(T obj, Node<T> next) {
    	this.item = obj;
    	this.next = next;
    	this.prev = null;
    }
    
    public Node(Node<T> prev, T obj, Node<T> next) {
    	this.prev = prev;
    	this.item = obj;
    	this.next = next;
    }
    
    public final T getItem() {
    	return item;
    }
    
    public Node<T> getNext() {
    	return this.next;
    }
    
    public Node<T> getPrev() {
    	return this.prev;
    }
    
    public final void setItem(T item) {
    	this.item = item;
    }
    
    public final void setNext(Node<T> next) {
    	this.next = next;
    }
    
    public final void setPrev(Node<T> prev) {
    	this.prev = prev;
    }
    
    public final void insertNext(T obj) {
    	Node<T> newNode = new Node<T>(this, obj, this.getNext());
    	this.getNext().setPrev(newNode);
    	this.setNext(newNode);
    }
    
    public final void removeNext() {
		this.setNext(this.getNext().getNext());
		this.getNext().setPrev(this);
    }
}