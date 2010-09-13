package misc;

class Bird {
	{
		System.out.println("Bird instance init ");
	}
	static {
		System.out.println("Bird static init 1");
	}

	public Bird() {
		System.out.println("Bird Constructor");
	}

	static {
		System.out.println("Bird static init 2");
	}
}

class Raptor extends Bird {
	static {
		System.out.println("Raptor static init 1");
	}
	{
		System.out.println("Raptor instance init ");
	}

	public Raptor() {
		System.out.println("Raptor Constructor");
	}

	static {
		System.out.println("Raptor static init 2");
	}
}

public class Hawk extends Raptor {

	static {
		System.out.println("Hawk static init 1");
	}

	{
		System.out.println("Hawk instance init ");
	}

	public Hawk() {
		System.out.println("Hawk Constructor");
	}

	static {
		System.out.println("Hawk static init 2");
	}

	public static void main(String[] args) {
		System.out.println(">>>> In main method Before instance ");
		new Hawk();
		System.out.println(">>>> In main method After instance ");
	}

	static {
		System.out.println("Hawk static init 3");
	}
}
