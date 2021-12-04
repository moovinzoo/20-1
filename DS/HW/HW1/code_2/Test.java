public class Test {
    public static void main(String[] args) {
        byte result1 = 0;
        byte result2 = 1;
        byte result3 = 0;
        byte result4 = (byte)((result1 - result2 + result3) + 10);

        System.out.println(result4);
    }
}
