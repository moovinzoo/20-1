public class HT<T extends Comparable> {
    private int size;
    private AVL[] table; // Container, table의 각 칸에 처음 삽입될 때 Instantiate 되어야 한다.

    public HT(int _size) {
        size = _size;
        table = new AVL[size];
    }

//    public void insert(Pair<Integer, Integer> location) {
//        String newKeyStr = Matching.stringList.get(location.getFirst()).substring(location.getSecond(), 6);
//        int hash = hashCode(newKeyStr);
    public void insert(String newKey, Pair<Integer, Integer> location) {
        int hash = hashCode(newKey);
//        if (table[hash].isEmpty()) {
//            table[hash] = new AVL<T>(newKey, location);
//        }
//        table[hash].insert(newKey, location);
    }

//
//    @Override
//    public int hashCode() {
//
//    }


    public int getSize() {
        return size;
    }

    public int hashCode(String strKey) {
        int charSum = 0;
        for (int i = 0; i < 6; i++) {
            charSum += strKey.charAt(i);
        }
        return (charSum % size);
    }
}
