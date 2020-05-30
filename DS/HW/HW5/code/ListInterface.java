public interface ListInterface<T> {
    public boolean isEmpty();
    public int size();
    public void add(T item);
    public void insert(int index, T item);
    public void remove(int index);
    public T get(int index);
    public void removeAll();
}
