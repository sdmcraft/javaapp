package misc;

public class EnumDemo {

	enum Day {
		MON, TUE, WED, THU, FRI, SAT, SUN;
		public static final String value = "a";
		Day() {
			System.out.println("Enum Constructor");
		}
	}

	public enum Test {
		INT(1),

		LONG(1L),

		SHORT((short) 1),

		BYTE((byte) 1);

		private Test(int i) {
			System.out.println("int");
		}

		private Test(long l) {
			System.out.println("long");
		}

		Test(short s) {
			System.out.println("short");
		}

		private Test(byte b) {
			System.out.println("byte");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println("Start");
//		for (Day d : Day.values()) {
//
//			System.out.println(d);
//			System.out.println(d.name());
//			System.out.println(d.ordinal());
//		}
//		method(Day.FRI);
		for(Test t : Test.values())
		{
			;
		}
	}

	public static void method(Enum e) {
		System.out.println(e);
	}

}
