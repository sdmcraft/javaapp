package algorithms;

public interface Dequeue {
	public void frontInsert(Object item);
	public void rearInsert(Object item);
	public Object frontRemove();
	public Object rearRemove();
}
