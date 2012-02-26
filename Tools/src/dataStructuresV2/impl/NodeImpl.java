package dataStructuresV2.impl;

import dataStructuresV2.Node;

public class NodeImpl<T> implements Node<T> {

	private final T value;

	public NodeImpl(T value) {
		this.value = value;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		return value.equals(o);
	}

}
