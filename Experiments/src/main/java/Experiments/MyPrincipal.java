package Experiments;

import java.security.Principal;

public class MyPrincipal implements Principal {

	final String name;

	public MyPrincipal(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		return name.equals(o);

	}

	public int hashcode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return name;
	}
}
