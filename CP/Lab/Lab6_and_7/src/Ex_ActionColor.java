import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Thread.sleep;

public class Ex_ActionColor extends Frame {
    Label la1;
    Label la2;
    Label la3;
    Label la4;
    Font f1;
    Font f2;
    Button b1;
    int count = 0;
    static Ex_ActionColor is;

    Ex_ActionColor() throws UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        super("Label Example");
//        UIManager.setLookAndFeel(new NimbusLookAndFeel());

        this.addWindowListener(new WinEvent());// 윈도우 이벤트 등록
        MyListener buttonlisten = new MyListener(); //for ButtonAction

        // Font setting //
        f1 = new Font("굴림체", Font.BOLD, 20);
        f2 = new Font("바탕체", Font.ITALIC, 25);

        // Label setting //
        la1 = new Label("RED"); // default : LEFT
        la2 = new Label("BLUE", Label.CENTER);
        la3 = new Label("GREEN", Label.RIGHT);
        la4 = new Label("PINK"); // default : LEFT

        // Button setting //
        b1 = new Button("Press_" + count);
        b1.addActionListener(buttonlisten);

        setLayout(new GridLayout(5, 1));
        la1.setFont(f1);
        la2.setFont(f1);
        la3.setFont(f2);
        la4.setFont(f2);
        add(la1);
        add(la2);
        add(la3);
        add(la4);
        add(b1);
        la1.setBackground(Color.red);
        la2.setBackground(Color.blue);
        la3.setBackground(Color.green);
        la4.setBackground(Color.pink);
        setSize(300, 250);
        setVisible(true);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        is = new Ex_ActionColor();
        Ex_ActionColor is2 = new Ex_ActionColor();
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.MetalLookAndFeel");
    }


    class MyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == b1) {
                switch (count%4) {
                    case 0:

                        la1.setAlignment((la1.getAlignment() + 1) % 3);
                        break;
                    case 1:
                        la2.setAlignment((la2.getAlignment() + 1) % 3);
                        break;
                    case 2:
                        la3.setAlignment((la3.getAlignment() + 1) % 3);
                        break;
                    case 3:
                        la4.setAlignment((la4.getAlignment() + 1) % 3);
                        break;
                    default:
                        break;
                }
                b1.setLabel("Press_" + (++count));
            }
        }
    }

    class WinEvent implements WindowListener {
        //윈도우가 활성화 될때
        public void windowActivated(WindowEvent e) {
            System.out.println("active");
            la1.setBackground(Color.red);
        }

        //윈도우가 닫힌 다음에 호출
        public void windowClosed(WindowEvent e) {

        }

        //윈도우가 닫히려고 할때 (X 버튼 누를때)
        public void windowClosing(WindowEvent e) {
            System.out.println("exit");
            //수정한 부분
            is.dispose();
            //
            //System.exit(0);// X를 누르면 종료된다.
        }

        //윈도우가 비활성화 될때
        public void windowDeactivated(WindowEvent e) {
            la1.setBackground(Color.GRAY);
            System.out.println("deactive");
        }

        //윈도우가 정상화 되었을때
        public void windowDeiconified(WindowEvent e) {
            System.out.println("Restored");
        }

        //윈도우가 아이콘화 되었을때
        public void windowIconified(WindowEvent e) {
            System.out.println("Minimized");
        }

        //윈도우가 화면에 처음 나타날때
        public void windowOpened(WindowEvent e) {
            System.out.println("open");
            la4.setBackground(Color.red);
            try {
                sleep(350);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            la4.setBackground(Color.pink);
            try {
                sleep(350);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            la4.setBackground(Color.red);
            try {
                sleep(350);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            la4.setBackground(Color.pink);
        }
    }
}

