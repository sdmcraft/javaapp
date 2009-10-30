package spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Canton {
	static String[] memory = new String[10000000];

	public static void main(String[] args) {
		BufferedReader reader = null;
		StringBuilder result = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			String line = "";
			line = reader.readLine();
			int testCaseCount = Integer.parseInt(line);
			memory[1] = "1/1;0@0#x";
			for (int i = 0; i < testCaseCount; i++) {
				line = reader.readLine();
				result.append(method(line));
				result.append(System.getProperty("line.separator"));
			}
			System.out.println(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	public static String method(String s) {
		int n = Integer.parseInt(s);
		if (memory[n] == null) {
			int step = 1;
			String lastStep = null;
			int lastJump = 0;
			int jump = 0;
			int num = 1;
			int den = 1;
			for (int i = n; i >= 0; i--) {
				if (memory[i] != null) {
					jump = Integer.parseInt(memory[i].substring(memory[i]
							.indexOf(';') + 1, memory[i].indexOf('@')));
					lastJump = Integer.parseInt(memory[i].substring(memory[i]
							.indexOf('@') + 1, memory[i].indexOf('#')));
					lastStep = memory[i].substring(memory[i].indexOf('#') + 1);

				}
			}
			while (true) {
				if ("x".equals(lastStep)) {
					den++;
					lastStep = "right";
					jump = 0;
				} else if ("right".equals(lastStep)) {
					num++;
					den--;
					lastStep = "down-left";
					jump++;
				} else if ("down-left".equals(lastStep)) {
					if (jump < (lastJump + 1)) {
						num++;
						den--;
						jump++;
					} else if (jump == (lastJump + 1)) {
						jump = 0;
						lastJump++;
						num++;
						lastStep = "down";
					}
				} else if ("down".equals(lastStep)) {
					num--;
					den++;
					lastStep = "up-right";
					jump++;
				} else if ("up-right".equals(lastStep)) {
					if (jump < (lastJump + 1)) {
						num--;
						den++;
						jump++;
					} else if (jump == (lastJump + 1)) {
						jump = 0;
						lastJump++;
						den++;
						lastStep = "right";
					}
				}
				memory[++step] = num + "/" + den + ";" + jump + "@" + lastJump
						+ "#" + lastStep;
				if (step > n)
					break;
			}
		}
		return "TERM " + n + " IS "
				+ memory[n].substring(0, memory[n].indexOf(';'));
	}
}
