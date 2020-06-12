public class CDLL<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;
    private int numItems;

    public CDLL() {
       numItems = 0;
       head = new Node<T>(null);
       // Circular doubly linked list
       head.setNext(head);
       head.setPrev(head);
       tail = head;
    }

    public boolean isEmpty() {
        return (numItems == 0);
    }
    public int size() {
        return numItems;
    }

    // List의 끝에 새 Node를 추가
    public void add(T item) {
        Node newNode = new Node(item, tail, head);
        tail.setNext(newNode);
        tail = newNode;
        head.setPrev(tail);
        numItems++;
    }

    // List의 중간에 새 Node를 삽입
    public void insert(int index, T item) {
        if (index >= 1 && index <= numItems) {
            Node curr = find(index);
            Node newNode = new Node(item, curr.getPrev(), curr);
            curr.getPrev().setNext(newNode);
            curr.setPrev(newNode);
            numItems++;
        } else {
            // TODO: exception
        }
    }

    // CDLL<Pair>의 경우엔 sorted order로 add
    public void add(Pair pair) {
        Node curr = head;
        while (pair.compareTo((Pair)curr.getItem()) > 0) {
            curr = curr.getNext();
        }

        Node newNode = new Node(pair, curr.getPrev(), curr);
        curr.getPrev().setNext(newNode);
        curr.setPrev(newNode);
        numItems++;
    }

    private Node<T> find(int index) {
        Node curr = head;
        for (int i = 0; i <= index; i++) {
            curr = curr.getNext();
        }
        return curr;
    }

    public void remove(int index) {
        if (index >= 1 && index <= numItems) {
            Node curr = find(index);
            curr.getPrev().setNext(curr.getNext());
            curr.getNext().setPrev(curr.getPrev());
            numItems--;
        } else {
            // TODO: exception
        }
    }

    public T get(int index) {
        return find(index).getItem();
    }

    public void removeAll() {
        numItems = 0;
        head = new Node<T>(null);
        head.setNext(head);
        head.setPrev(head);
        tail = head;
    }
}
