package dataStructureHW2;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements ListInterface<T> {
	// dummy head
	Node<T> head;
	// dummy tail
	Node<T> tail;
	
	int numItems;

	public MyLinkedList() {
		head = new Node<T>(null);
		tail = new Node<T>(null);
		head.setNext(tail);
		tail.setPrev(head);
		numItems = 0;
	}

    /**
     * {@code Iterable<T>}를 구현하여 iterator() 메소드를 제공하는 클래스의 인스턴스는
     * 다음과 같은 자바 for-each 문법의 혜택을 볼 수 있다.
     * 
     * <pre>
     *  for (T item: iterable) {
     *  	item.someMethod();
     *  }
     * </pre>
     * 
     * @see PrintCmd#apply(MovieDB)
     * @see SearchCmd#apply(MovieDB)
     * @see java.lang.Iterable#iterator()
     */
    public final Iterator<T> iterator() {
    	return new MyLinkedListIterator<T>(this);
    }

	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	@Override
	public int size() {
		return numItems;
	}

	@Override
	public T first() {
		return head.getNext().getItem();
	}
	
	public Node<T> nodeAt(int i) {//dummy head is in index 0! first user-input one is in index 1.
		Node<T> cursor = head;
		for (int j=0; j<i; j++) {
			cursor = cursor.getNext();
		}
		return cursor;
	}

	@Override
	public void add(T item) {
		/* old code (in skeleton)
		Node<T> last = head;
		while (last.getNext() != null) {
			last = last.getNext();
		}
		last.insertNext(item);
		*/
		tail.getPrev().insertNext(item);
		numItems += 1;
	}

	@Override
	public void removeAll() {
		head.setNext(tail);
		tail.setPrev(head);
		numItems = 0;
	}
}

class MyLinkedListIterator<T> implements Iterator<T> {
	// FIXME implement this
	// Implement the iterator for MyLinkedList.
	// You have to maintain the current position of the iterator.
	private MyLinkedList<T> list;
	private Node<T> curr;
	private Node<T> prev;

	public MyLinkedListIterator(MyLinkedList<T> list) {
		this.list = list;
		this.curr = list.head;
		this.prev = null;
	}

	@Override
	public boolean hasNext() {
		return curr.getNext() != list.tail;
	}

	@Override
	public T next() {
		if (!hasNext())
			throw new NoSuchElementException();

		prev = curr;
		curr = curr.getNext();

		return curr.getItem();
	}

	@Override
	public void remove() {
		if (prev == null)
			throw new IllegalStateException("next() should be called first");
		if (curr == null)
			throw new NoSuchElementException();
		prev.removeNext();
		list.numItems -= 1;
		curr = prev;
		prev = null;
	}
}