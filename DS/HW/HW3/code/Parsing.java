import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

// FIXME: 2020/04/27 DEVELOP TO THROW EXCEPTION
public class Parsing {
    
    
    public static Queue<Element> parseString(String input) {
        // TODO: 2020/04/26 This method takes input and parse this string into element
        Queue<Element> resultQueue = new LinkedList<>();
        
        
        return resultQueue;
    }
    
    public static Stack<Element> infixToPostfix(String input){
        // FIXME: 2020/04/26 이렇게하면 throw 되나?
        checkInputContainsWrong(input);

        Queue<Element> parsedString = parseString(input);

        Stack<Element> resultStack = new Stack<>();

        
        return resultStack;
    }
    
    // 가장 먼저, input string에 허용되지 않는 문자가 있는 경우 Exception을 던진다.
    public static boolean checkInputContainsWrong(String input) {
        if(!input.matches("[0-9\t( )(   )/%^+*)(-]*")) return false;
        return true;
    }
    // TODO: 2020/04/27 아래 친구들처럼 예외반영 할 수 있도록 수정 요. 
    //    public static boolean checkInputContainsWrong(String input) throws Exception {
    //    if(!input.matches("[0-9, ,+,/,*,%,^,(,),-]*")) {
    //            throw new Exception("ERROR");
}
