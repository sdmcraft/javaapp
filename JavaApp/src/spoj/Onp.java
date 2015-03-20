package spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class CharStackImpl {

    private char elements[];
    private int top;

    public CharStackImpl(int size) throws Exception{
        if(size < 0)
            throw new Exception("Size cannot be < 0");
        top = -1;
        elements = new char[size];
    }


    public char pop() throws Exception {
        if (top < 0) {
            throw new Exception("Nothing to pop!!!");
        }
        return elements[top--];
    }

    public void push(char item) throws Exception {
        if (top >= elements.length - 1) {
            throw new Exception("Stack full. Cannot push!!!");
        }
        elements[++top] = item;
    }

    public char peek() throws Exception{
        if (top < 0) {
            throw new Exception("Nothing to peek!!!");
        }
        return elements[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

public class Onp {    

	public static String convert(String infix) throws Exception {        
        char input[] = infix.toCharArray();
        StringBuffer op = new StringBuffer(input.length);
        CharStackImpl operatorStack = new CharStackImpl(input.length);
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
		BufferedReader reader = null;
		StringBuilder result = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			String line = "";
			line = reader.readLine();
			int testCaseCount = Integer.parseInt(line);
			for (int i = 0; i < testCaseCount; i++) {
				line = reader.readLine();
				result.append(convert(line));
				result.append(System.getProperty("line.separator"));
			}
			System.out.println(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}

    }
}
