public class Node<T> {
    private T item;
    private Node next;

    public Node() {
    }

    public Node (T _item) {
        item = _item;
        next = null;
    }

    public Node(T _item, Node _next) {
        item = _item;
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

    public Node getNext() {
        return next;
    }
}
