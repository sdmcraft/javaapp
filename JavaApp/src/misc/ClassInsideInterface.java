package misc;

public interface ClassInsideInterface {

	public class Inner
	{
		public void method()
		{
			System.out.println("Hi!!");
		}
	}
}

class X implements ClassInsideInterface
{
	
	public static void main(String[] args) {
		Inner inner = new Inner();
		inner.method();
	}
}
