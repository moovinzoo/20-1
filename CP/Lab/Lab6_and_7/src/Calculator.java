import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Calculator extends Frame implements ActionListener {

    private TextField input;
    private Button bt;
    private boolean state=true;//입력진행모드 true 면 한자리 숫자. false 면 한자리 이상
    private int op;//연산자 구분 번호로 +는1, -는 2, *는 3, /는 4
    private double buffer=0;//숫자 저장소
    private boolean opstate=true;//연산자를 버튼을 연속으로 눌렀는지의 여부, 연속으로 누른다면 true

    // 메인 메서드
    public static void main(String[] args) {

        Calculator cal=new Calculator();

    }

    // 생성자
    Calculator(){

        super("CP2020 계산기");

        Panel main=new Panel();
        main.setLayout(new BorderLayout());

        input = new TextField();

        main.add("North",input);

        Panel p=new Panel();
        p.setLayout(new GridLayout(4,4));
        // 버튼 부착하고 이벤트 리스너 연결하기
        String s="789+456-123*0C=/";
        for(int i=0;i<s.length();i++){
            bt=new Button(s.substring(i,i+1));

            p.add(bt);
            bt.addActionListener(this);
        }

        main.add("Center",p);
        add(main);

//종료버튼 달기
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setSize(400,240);
        setSize(400,240);

        setVisible(true);

    }

    // ActionListener 오버라이딩 메서드 actionPerformed()
    public void actionPerformed(ActionEvent ae){

        String val=ae.getActionCommand();//이벤트가 발생한 버튼을 문자열로 리턴
        if(val.equals("C")){
            input.setText("");
            buffer=0;
//숫자버튼을 눌렸는지 검사. 이때 숫자를 연속해서 누르면 십단위가 되고
//아님 1단위가 된다.
//연속해서 눌렀는지를 체크하는 변수 state
        }else if('0' <= val.charAt(0) && val.charAt(0) <= '9'){
            if (state) input.setText(val);
            else input.setText(input.getText()+val);
            state = false;

//숫자 버튼이 아니라면 연산버튼이므로 연산을 처리하는 메서드 Operator(var)를 호출.
        }else
            Operator(val);
    }

    // 연산을 처리하는 메서드 Operator(var)
    public void Operator(String val){

        if(val.equals("+")){
            op=1;
            if(opstate) result();
            opstate=true;
        }else if(val.equals("-")){
            op=2;
            if(opstate) result();
            opstate=true;
        }else if(val.equals("*")){
            op=3;
            if(opstate) result();
            opstate=true;
        }else if(val.equals("/")){
            op=4;
            if(opstate) result();
            opstate=true;
        }else {
            result();
        }
        buffer= (new Double(input.getText())).doubleValue();
        state = true;
    }
    //= 버튼을 누르면 호출되는 결과처리 메서드.
    public void result(){
        double result=0;
        double in=(new Double(input.getText())).doubleValue();
        switch(op){
            case 1:
                result=buffer+in;
                break;
            case 2:
                result=buffer-in;
                break;
            case 3:
                result=buffer*in;
                break;
            case 4:
                result=buffer/in;
                break;
        }
        input.setText(result+"");
        opstate=false;
    }

}