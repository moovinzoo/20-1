import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;



/*
 * Event Handler
 * : Overriding interface
 *
 */
public class practice_ActionEventEx extends Frame implements ActionListener,WindowListener {
    Panel p;
    Button input, exit;
    TextArea ta;

    public practice_ActionEventEx() {

        super("ActionEvent Test");
        p = new Panel();

        input = new Button("Input");
        exit = new Button("Terminate");
        ta = new TextArea();

        input.addActionListener(this);
        exit.addActionListener(this);
        addWindowListener(this);

        p.add(input);
        p.add(exit);

        add(p, BorderLayout.NORTH);
        add(ta, BorderLayout.CENTER);

        setBounds(1000,300,300,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String name = ae.getActionCommand();

        if (name.equals("Input"))
            ta.append("Button has been clicked.\n");
        else {
            ta.append("Terminate program\n");

            try {   // 의무적으로 예외처리
                Thread.sleep(3000);
            }catch(Exception e) {}

            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new practice_ActionEventEx();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}