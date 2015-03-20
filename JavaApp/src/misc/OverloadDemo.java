package misc;

interface Life {
	public int eat();
}

class Mammal implements Life {
	public int eat() {
		return 1;
	}

	public Life reproduce() {
		return new Mammal();
	}
}

class Reptile implements Life {
	public int eat() {
		return 2;
	}
}

class Feline extends Mammal {
	public int eat(int a) {
		return 3;
	}

	public Mammal reproduce(int a) {
		return new Mammal();
	}

	public Feline reproduce() {
		return new Feline();
	}
}

class Canine extends Mammal {
}

public class OverloadDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
