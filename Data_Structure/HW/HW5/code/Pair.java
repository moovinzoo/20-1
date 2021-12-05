public class Pair implements Comparable<Pair> {
    private int first;
    private int second;

    public Pair(int _first, int _second) {
        first = _first;
        second = _second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("(");
        sb.append(first);
        sb.append(", ");
        sb.append(second);
        sb.append(")");
        return sb.toString();
    }

    @Override
    public int compareTo(Pair o) {
        if (first > o.getFirst()) return 1;
        if (first == o.getFirst()) {
            if (second > o.getSecond()) return 1;
            if (second == o.getSecond()) return 0;
            return -1;
        }
        return -1;
    }
}
