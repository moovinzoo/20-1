import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;;


public class practice_ButtonLis {

    private Button b1 = new Button("input");//Button create
    private Button b2 = new Button("output");
    private Label l1 = new Label("1");


    private class MyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == b1) {
                b1.setLabel("clicked");
                l1.setText("2");

            }
            else if(e.getSource() == b2) {
                b2.setLabel("clicked");
                l1.setText("3");
            }
        }
    }

    public void hello() {

        Frame f = new Frame("button test") ;//Frame create

        MyListener buttonlisten = new MyListener(); //for ButtonAction

        /* Frame windowListener */
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Dispose the window after the close button is clicked.
                System.exit(0);
            }
        });
        /*--------------------------------*/

        Panel p = new Panel();//Panel create

        b1.addActionListener(buttonlisten);
        b2.addActionListener(buttonlisten);
        p.add(b1);// Attach name to button
        p.add(b2);
        p.add(l1);
        f.add(p);// Attach panel at frame
        f.setLocation(300,300);//Frame location
        f.setSize(300,100);//Frame size
        f.setVisible(true);//Frame create
    }
    public static void main(String[] args) {

        practice_ButtonLis calc = new practice_ButtonLis();
        calc.hello();
    }

}