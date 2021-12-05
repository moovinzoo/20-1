import javax.swing.*;
import java.awt.*;

class DialogTest {
    public static void main(String[] args) {
        JFrame f = new JFrame("Parent");
        f.setSize(300, 200);

        // parent Frame을 f로 하고, modal을 true로 해서 필수 응답 dialog로 함.
        JDialog info = new JDialog(f, "Information", true);
        info.setSize(140, 90);

        // parent Frame이 아닌, 화면이 위치의 기준이 된다.
        info.setLocation(50, 50);
        info.setLayout(new FlowLayout());
        JLabel msg = new JLabel("This is modal JDialog", JLabel.CENTER);
        JButton ok = new JButton("OK");
        info.add(msg);
        info.add(ok);
        f.setVisible(true);

        // Dialog를 화면에 보이게
        info.setVisible(true);
    }
}
