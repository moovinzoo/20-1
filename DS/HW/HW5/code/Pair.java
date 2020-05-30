public class Pair<T, E> implements Comparable {
    private T first;
    private E second;

    public Pair(T _first, E _second) {
        first = _first;
        second = _second;
    }

    public T getFirst() {
        return first;
    }

    public E getSecond() {
        return second;
    }

//    @Override
//    public int compareTo(Object o) {
//        if (this.getClass() == o.getClass()) {
//            Pair<T, E> pairedOpponent = (Pair)o;
//            int compareFirst, compareSecond;
//            int compareFirst = pairedOpponent
//            if (pairedOpponent.getFirst() == this.getFirst()) {
//                if (pairedOpponent.getSecond() == this.getSecond()) {
//
//                }
//            }
//        }
//
//        return -1; // 타입이 다를 때 (Exception 처럼 사용)
//    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("(");
        sb.append(first);
        sb.append(", ");
        sb.append(")");
        return sb.toString();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
