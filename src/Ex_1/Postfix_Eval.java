package Ex_1;

import java.util.Stack;

import static Ex_1.To_postfix.toPostfix;

public class Postfix_Eval {

    static int postfixEval(String str){
        
        Stack<Integer> stack = new Stack<>(); // cream o stiva de nr intregi
        
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            
            if(Character.isDigit(ch)){
                stack.push(Character.getNumericValue(ch));
            }else{ // caracterul curent este operator - luam doua elemente din stiva pe care urmeaza sa aplicam operatorul

                int op1 = stack.pop();
                int op2 = stack.pop();

                switch(ch){
                    case '+':
                        stack.push(op2 + op1);
                        break;
                    case '-':
                        stack.push(op2 - op1);
                        break;
                    case '*':
                        stack.push(op2 * op1);
                        break;
                    case '/':
                        stack.push(op2 / op1);
                        break;
                    case '^':
                        stack.push(result(op1, op2));
                }
            }
        }
        return stack.pop();
    }

    public static int result(int op1, int op2){
        int result = 1;
        for(int i = 1; i <= op1; i++){
            result *= op2;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "3+(2+1)*2^3^2-8/(5-1*2/2)"; //3 2 1 + 2 3 2 ^ ^ * + 8 5 1 2 * 2 / - / -
        System.out.println(toPostfix(s));
        System.out.println(postfixEval(toPostfix(s))); //1537


    }

}
