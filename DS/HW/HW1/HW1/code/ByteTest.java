public class ByteTest {
    public static void main(String[] args) {
        byte[] byteArr1 = {1, 2, 3, 4, 5};
        byte[] byteArr2 = {1, 2, 3, 4, 5};
        byte[] byteArr3 = new byte[5];

        for (int i = 0; i < byteArr1.length; i++) {
            byte left = byteArr1[i];
            byte right = byteArr2[i];
            byteArr3[i] = (byte) ((left + right) % 10);
        }

        String tmpStr = "";
        for (int i = 0; i < byteArr3.length; i++) {
            tmpStr += byteArr3[i];
            tmpStr += " ";
        }
        System.out.println(tmpStr);
    }
}
