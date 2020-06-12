import javax.swing.*;
import java.awt.*;

class Grid1 {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        Frame frame = new Frame();
//        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        String[] msg = {"Bottom", "Top", "Right", "Left", "Center"};
        Color[] color = {Color.RED, Color.GREEN, Color.blue, Color.pink, Color.CYAN};
        String[] location = {"South", "North", "East", "West", "Center"};
        Button[] btn = new Button[msg.length];
        for (int i = 0; i < btn.length; i++) {
            btn[i] = new Button(msg[i]);
            btn[i].setBackground(color[i]);
            frame.add(btn[i], location[i]);
        }

        frame.setSize(300, 300);
        frame.setVisible(true);

    }
}
//public class Grid1 extends Frame {
//    Font ft_btn;
//    Button btn[] = new Button[6];
//
//    public Grid1(String str) {
//        super(str);
//        setLayout(new GridLayout(6, 1));
//        ft_btn = new Font("굴림체", Font.BOLD, 20);
//
//        setBackground(Color.GREEN);
//
//        for (int i = 0; i < 6; i++) {
//            btn[i] = new Button(i+1 + " button");
//            btn[i].setFont(ft_btn);
//            btn[i].setBackground(Color.RED);
//            btn[i].setForeground(Color.BLUE);
//            add(btn[i]);
//        }
//        setSize(200, 200);
//        setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        new Grid1("Grid test");
//    }
//}
