package misc;

public class EnumDemo {

	enum Day {
		MON, TUE, WED, THU, FRI, SAT, SUN;
		Day() {
			System.out.println("Enum Constructor");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start");
		for (Day d : Day.values()) {
			
			System.out.println(d);
			System.out.println(d.name());
			System.out.println(d.ordinal());
		}
		method(Day.FRI);
	}

	public static void method(Enum e) {
		System.out.println(e);
	}

}
