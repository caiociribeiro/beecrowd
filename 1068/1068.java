import ed.stack.Stack;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            Stack<Character> s = new Stack<>();

            String exp = in.nextLine();

            for (int i = 0; i < exp.length(); i++) {
                s.push(exp.charAt(i));
            }

            boolean isCorrect = true;
            int openParentheses = 0;
            int closeParentheses = 0;
            while (!s.isEmpty()) {
                char c = s.pop();

                if (c == '(') {
                    // esse check so eh necessario para o '('
                    // para saber se tem pelo menos 1 ')' para esse '('
                    if (openParentheses >= closeParentheses) {
                        isCorrect = false;
                        break;
                    }
                    openParentheses++;
                }

                if (c == ')') {
                    closeParentheses++;
                }
            }

            if (openParentheses != closeParentheses)
                isCorrect = false;

            System.out.println(isCorrect ? "correct" : "incorrect");
        }
    }
}
