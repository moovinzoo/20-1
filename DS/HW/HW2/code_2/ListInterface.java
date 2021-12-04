public interface ListInterface<T> extends Iterable<T> {
//	public interface ListInterface<T extends Comparable<T>> extends Iterable<T> {
	public boolean isEmpty();

	public int size();

	public void add(T item);

	public T first();

	public void removeAll();
}
//public interface ListInterface<T> extends Iterable<T> {
//	public boolean isEmpty();
//
//	public int size();
//
//	public void add(T item);
//
//	public T first();
//
//	public void removeAll();
//}
