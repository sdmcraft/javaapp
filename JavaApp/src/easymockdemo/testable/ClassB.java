package easymockdemo.testable;

public class ClassB {

	public void method() {
		ClassA a1 = new ClassA();
		ClassA a2 = new ClassA(1);
		a2.methodA1();
	}
}
