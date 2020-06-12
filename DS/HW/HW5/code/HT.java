public class HT<T extends Comparable<T>> {
    private int size;
    private AVL<T>[] table; // Container, table의 각 칸에 처음 삽입될 때 Instantiate 되어야 한다.

    public HT(int _size) {
        size = _size;
        table = (AVL<T>[])new AVL[size];
    }

    public void insert(String newKey, T location) {
        int hash = hashCode(newKey);
        if (table[hash] == null) {
            table[hash] = new AVL<T>();
        }
        table[hash].add(newKey, location);
    }

    public int getSize() {
        return size;
    }

    public void print(int index) {
       if (table[index] == null) {
           System.out.println("EMPTY");
       } else {
          table[index].preorder();
       }
    }

    public int hashCode(String strKey) {
        int charSum = 0;
        for (int i = 0; i < 6; i++) {
            charSum += strKey.charAt(i);
        }
        return (charSum % size);
    }
}
