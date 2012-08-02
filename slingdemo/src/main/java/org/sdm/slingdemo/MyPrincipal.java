package org.sdm.slingdemo;

import java.security.Principal;

public class MyPrincipal implements Principal {

	private final String name;
	
	
	public MyPrincipal(String name) {
		super();
		this.name = name;
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
