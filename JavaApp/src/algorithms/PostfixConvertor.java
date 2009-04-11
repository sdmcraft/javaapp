package algorithms;

public class PostfixConvertor {    
    public PostfixConvertor() {
    }
    
    public static String convert(String infix) throws Exception {        
        char input[] = infix.toCharArray();
        StringBuffer op = new StringBuffer(input.length);
        CharStack operatorStack = new CharStackImpl(input.length);
        for(int i=0;i<input.length;i++) {
            char c = input[i];
            if((c=='+') || (c=='-') || (c=='*') || (c=='/') || (c=='^')) {
                while(!operatorStack.isEmpty()) {
                    char op2 = operatorStack.peek();
                    if(op2=='(')
                        break;
                    else {
                        int result = compareOperators(c,op2);
                        if(result>0)
                            break;
                        else
                            op.append(operatorStack.pop());
                    }
                }
                operatorStack.push(c);                
            } else if(c=='(') {
                operatorStack.push(c);
            } else if(c==')') {
                while(!operatorStack.isEmpty()) {
                    char operator = operatorStack.pop();
                    if(operator=='(')
                        break;
                    else
                        op.append(operator);
                }
            } else {
                op.append(c);
            }
        }
        while(!operatorStack.isEmpty()) {
            op.append(operatorStack.pop());
        }        
        return new String(op);
    }
    
    private static int compareOperators(char op1,char op2) {
        int precedence1=0;
        int precedence2=0;
        switch(op1) {
            case '+':precedence1=0;
            break;
            case '-':precedence1=0;
            break;
            case '*':precedence1=1;
            break;
            case '/':precedence1=1;
            break;
            case '^':precedence1=2;
            break;
        }
        switch(op2) {
            case '+':precedence2=0;
            break;
            case '-':precedence2=0;
            break;
            case '*':precedence2=1;
            break;
            case '/':precedence2=1;
            break;
            case '^':precedence2=2;
            break;
        }
        return precedence1-precedence2;
    }
    
    public static void main(String... args) throws Exception {
        //System.out.println(PostfixConvertor.convert("(a+b)/c*(d+e/(f+g))-h"));
    }
}
