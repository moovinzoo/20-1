import java.awt.*;
import java.nio.file.StandardWatchEventKinds;

public class Test2 {
    public static void main(String[] args) {
        Frame f = new Frame("radio cheeckbox test");
        Panel p = new Panel();

        TextField tf1 = new TextField("ID", 12);
        TextField tf2 = new TextField("PW", 10);

        tf1.selectAll();
        tf2.selectAll();

        tf2.setEchoChar('*');

        p.add(tf1);
        p.add(tf2);
        f.add(p);

//        f.setLocation(300, 300);
        f.setSize(300, 100);
        f.setVisible(true);

    }
}
