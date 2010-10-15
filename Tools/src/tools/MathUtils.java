package tools;

import dataStructures.LinkedList;

public class MathUtils {

	private static String add(String string1, String string2) {
		LinkedList number1 = new LinkedList(string1);
		LinkedList number2 = new LinkedList(string2);
		LinkedList number1Rev = number1.reverse();
		LinkedList number2Rev = number2.reverse();		
		StringBuilder result = new StringBuilder();
		int carry = 0;
		while (true) {
			int sum = carry + Integer.parseInt(number1Rev.getValue())
					+ Integer.parseInt(number2Rev.getValue());
			result.append(sum % 10);
			carry = sum / 10;
			if (number1Rev.hasNext() && number2Rev.hasNext()) {
				number1Rev = number1Rev.getNext();
				number2Rev = number2Rev.getNext();
				
			} else
				break;
		}
		while (number1Rev.hasNext()) {
			int sum = carry + Integer.parseInt(number1Rev.getNext().getValue());
			result.append(sum % 10);
			carry = sum / 10;
		}

		while (number2Rev.hasNext()) {
			int sum = carry + Integer.parseInt(number2Rev.getNext().getValue());
			result.append(sum % 10);
			carry = sum / 10;
		}
		if(carry > 0)
			result.append(carry);
		return result.reverse().toString();
	}
	
	public static void main(String[] args) {
		System.out.println(add("123456789","987654321"));//21+99 = 111
		System.out.println(123456789L+987654321L);
	}
}