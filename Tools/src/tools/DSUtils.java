package tools;

public class DSUtils {
	public static boolean arrayCompare(int[] src, int[] tgt) {
		if (src == null || tgt == null || src.length != tgt.length)
			return false;
		else {
			for (int i = 0; i < src.length; i++) {
				if (src[i] != tgt[i])
					return false;
			}
		}
		return true;
	}

	public static int[] stringArrayToIntArray(String[] data) {
		int[] result = new int[data.length];
		int i = 0;
		for (String s : data)
			result[i++] = Integer.parseInt(s);
		return result;
	}

	public static String[] intArrayToStringArray(int[] data) {
		String[] result = new String[data.length];
		int i = 0;
		for (int a : data)
			result[i++] = Integer.toString(a);
		return result;
	}

}
