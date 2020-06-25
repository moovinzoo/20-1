import java.awt.*;
import java.nio.file.StandardWatchEventKinds;

public class Test2 {
    public static void main(String[] args) {
        String str1 = new String("Good");
        String str2 = str1;
        str1 += "Morning";
        System.out.println("str1: " + str1 + "str2: " + str2);
        StringBuffer sb1 = new StringBuffer("Good");
        StringBuffer sb2 = sb1;
        sb1.append("Morning");
        System.out.println("sb1: " + sb1 + "sb2: " + sb2);
    }
}
