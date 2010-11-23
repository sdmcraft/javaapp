package dataStructures;

import java.util.ArrayList;
import java.util.List;

/**
 * An array with variable sized elements in terms of bits. Internally this is a
 * list of variable sized boolean arrays.
 */
public class BitArray {
	private final List<boolean[]> dataList = new ArrayList<boolean[]>();

	public void add(long data) {
		String bitStr = Long.toBinaryString(data);
		boolean[] bits = new boolean[bitStr.length()];
		for (int i = 0; i < bitStr.length(); i++)
			if (bitStr.charAt(i) == '1')
				bits[i] = true;
		dataList.add(bits);
	}

	private String getBits(int index) {
		boolean[] bits = dataList.get(index);

		StringBuilder sb = new StringBuilder(bits.length);
		for (int i = 0; i < bits.length; i++)
			if (bits[i])
				sb.append('1');
			else
				sb.append('0');
		return sb.toString();
	}

	public long get(int index) {
		return Long.parseLong(getBits(index), 2);
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
