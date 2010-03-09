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
}
