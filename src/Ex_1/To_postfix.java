package Ex_1;

import java.util.Stack;

public class To_postfix {

    static int aux_preced(char c){
        if(c == '+' || c == '-'){
            return 1;
        }else if(c == '*' || c == '/'){
            return 2;
        }else if(c == '^'){
            return 3;
        }else{
            return 0;
        }
    }

   public static String toPostfix(String str){
        
        String postfix_form = new String("");

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i<str.length(); ++i){
            char c = str.charAt(i);

            if (Character.isLetterOrDigit(c))
                postfix_form += c;  // concatenez caracterele la string


            else if (c == '('){
                stack.push(c); // daca intalnesc o paranteza deschisa o adaug la stiva
            }else if (c == ')'){ // daca intalnesc o paranteza inchisa
                while (!stack.isEmpty() && stack.peek() != '(') { // caut in stiva prima paranteza deschisa
                    postfix_form += stack.pop(); // si adaug toate elementele de pana la ea in string
                }
                stack.pop(); // cand gasesc paranteza inchisa o sterg din stiva

            }else{ // cand intalnesc un operator
                while (!stack.isEmpty() && aux_preced(c) <= aux_preced(stack.peek())){
                    // cat timp stiva nu e goala si precedenta caracterului curent este <= decat precedenta elementului din varful stivei
                    // TODO: 4/13/2022 are o problema cu '^' consecutive-- asa ca am pus conditia asta..
                    if(aux_preced(c) == 3){
                        break;
                    }
                    postfix_form += stack.pop(); // adaug la string elementul din varful stivei si il sterg din stiva
                }
                stack.push(c); //adaug in stiva elementul curent
            }
        }
        while (!stack.isEmpty()){
            if(stack.peek() == '(')
                return "Invalid expression!";
            postfix_form += stack.pop(); // adaugam toti operatorii la string
        }
        return postfix_form;
    }

    public static void main(String[] args) {
        String s = "3+(2+1)*2^3^2-8/(5-1*2/2)"; //3 2 1 + 2 3 2 ^ ^ * + 8 5 1 2 * 2 / - / -
        System.out.println(toPostfix(s));
        String s2 = "3+(2+1)*2^3-8/(5-1*2/2)"; //3 2 1 + 2 3 ^ * + 8 5 1 2 * 2 / - / -
        System.out.println(toPostfix(s2));
        String s3 = "3+(2+1)*2^3*2*3^2-8/(5-1*2/2)"; //3 2 1 + 2 3 ^ * 2 * 3 2 ^ * + 8 5 1 2 * 2 / - / -
        System.out.println(toPostfix(s3));
    }




}
