package algorithms;
import algorithms.StackImpl;

public class ExpressionEvaluator {
    
    /** Creates a new instance of ExpressionEvaluator */
    public ExpressionEvaluator() {
    }
    
    public static int evaluate(String expression) throws Exception {
        String postFixExp = PostfixConvertor.convert(expression);
//        System.out.println("Postfix:"+postFixExp);
        return evaluatePostFix(postFixExp);
    }
    
    public static int evaluatePostFix(String exp) throws Exception {
        char[] input = exp.toCharArray();
        Stack operandStack = new StackImpl(input.length);
        for(int i=0;i<input.length;i++) {
            char c = input[i];
            if((c=='+') || (c=='-') || (c=='*') || (c=='/') || (c=='^')) {
                int opnd1 = operandStack.pop();
                int opnd2 = operandStack.pop();
                switch(c) {
                    case '+':operandStack.push(opnd2 + opnd1);
                    break;
                    case '-':operandStack.push(opnd2 - opnd1);
                    break;
                    case '*':operandStack.push(opnd2 * opnd1);
                    break;
                    case '/':operandStack.push(opnd2 / opnd1);
                    break;
                    case '^':operandStack.push((int)Math.pow(opnd2,opnd1));
                    break;
                }
            } else
                operandStack.push(c - '0');
        }
        return operandStack.pop();
    }
}

