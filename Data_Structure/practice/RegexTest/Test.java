import java.util.regex.Matcher;

class Test {
    public static void main(String args[]) {
        String operatorChunk = "+";
        if (!operatorChunk.matches("[\\)]*[\\^\\*\\/\\%\\-\\+][\\-\\(]*") && !operatorChunk.matches("[^*/%+-][-(]*")) {
            System.out.println("Do not match!");
        } else {
            System.out.println("Matches!");
        }
    }
}
