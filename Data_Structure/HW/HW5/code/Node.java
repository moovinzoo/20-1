public class Node<T> {
    private T item;
    private Node prev;
    private Node next;

    public Node() {
    }

    public Node (T _item) {
        item = _item;
        next = null;
    }

    public Node(T _item, Node _prev, Node _next) {
        item = _item;
        prev = _prev;
        next = _next;
    }

    public void setItem(T _item) {
        item = _item;
    }

    public T getItem() {
        return item;
    }

    public void setNext(Node _next) {
        next = _next;
    }

    public void setPrev(Node _prev) {
        prev = _prev;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }
}
