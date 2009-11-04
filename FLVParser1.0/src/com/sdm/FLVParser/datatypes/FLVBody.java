package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class FLVBody.
 */
public class FLVBody {
	
	/** The component list. */
	List<FLVBodyComponent> componentList = new ArrayList<FLVBodyComponent>();

	/**
	 * Instantiates a new fLV body.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public FLVBody(PushbackInputStream in) throws Exception {
		while (in.available() > 0)
			componentList.add(new FLVBodyComponent(in));
	}

	/**
	 * Write.
	 * 
	 * @param out the out
	 * 
	 * @throws Exception the exception
	 */
	public void write(OutputStream out) throws Exception {
		for (FLVBodyComponent bodyComp : componentList)
			bodyComp.write(out);
	}

	/**
	 * Gets the component list.
	 * 
	 * @return the component list
	 */
	public List<FLVBodyComponent> getComponentList() {
		return componentList;
	}

	/**
	 * Sets the component list.
	 * 
	 * @param componentList the new component list
	 */
	public void setComponentList(List<FLVBodyComponent> componentList) {
		this.componentList = componentList;
	}
}
