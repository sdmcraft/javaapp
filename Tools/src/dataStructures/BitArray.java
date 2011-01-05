package dataStructures;

import java.util.ArrayList;
import java.util.List;

import tools.DSUtils;

/**
 * An array with variable sized elements in terms of bits. Internally this is a
 * list of variable sized boolean arrays.
 */
public class BitArray {
	private final List<boolean[]> dataList = new ArrayList<boolean[]>();

	public void add(long data) {
		String bitStr = Long.toBinaryString(data);
		boolean[] bits = DSUtils.bitStringToBooleanArray(bitStr);
		dataList.add(bits);
	}

	private String getBits(int index) {
		boolean[] bits = dataList.get(index);
		return DSUtils.booleanArrayToBitString(bits);
	}

	public long get(int index) {
		return Long.parseLong(getBits(index), 2);
	}

	public void set(int index, long data) {
		String bitStr = Long.toBinaryString(data);
		boolean[] bits = DSUtils.bitStringToBooleanArray(bitStr);
		dataList.set(index, bits);
	}

	public void leftExpand(int offset) {
		for (int i = 0; i < offset; i++)
			dataList.add(0, new boolean[1]);
	}

	private void rightExpand(int offset) {
		for (int i = 0; i < offset; i++)
			dataList.add(new boolean[1]);
	}

	public void increment(int index) {
		if (index > dataList.size() -1)
			rightExpand(index - dataList.size() + 1);
		long value = Long.parseLong(getBits(index), 2) + 1;
		set(index, value);
	}

	public int length() {
		return dataList.size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < dataList.size(); i++) {
			sb.append(getBits(i));
			sb.append(',');
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("]");
		return sb.toString();
	}

	public static void main(String[] args) {
		BitArray bitArray = new BitArray();
		bitArray.add(5);
		bitArray.add(56);
		bitArray.add(5000);
		System.out.println(bitArray);
		System.out.println(bitArray.get(1));
	}

	public List<boolean[]> getDataList() {
		return dataList;
	}
}
