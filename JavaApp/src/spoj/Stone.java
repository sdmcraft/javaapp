package spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Stone {
	public static void main(String[] args) {
		BufferedReader reader = null;
		StringBuilder result = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			String line = "";
			line = reader.readLine();
			int testCaseCount = Integer.parseInt(line);
			for (int i = 0; i < testCaseCount; i++) {
				line = reader.readLine();
				int polygonSides = Integer.parseInt(line);
				int[] x = new int[polygonSides];
				int[] y = new int[polygonSides];
				for (int j = 0; j < polygonSides; j++) {
					line = reader.readLine();
					x[j] = Integer.parseInt(line.split(" ")[0]);
					y[j] = Integer.parseInt(line.split(" ")[1]);
				}
				result.append(polygonCentroid(x, y));
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

	private static double polygonArea(int[] x, int[] y) {
		double area = 0;
		int n = x.length;
		for (int i = 0; i < n; i++) {
			area += x[i % n] * y[(i + 1) % n] - x[(i + 1) % n] * y[i % n];
		}
		return Math.abs(area / 2);
	}

	private static String polygonCentroid(int[] x, int[] y) {
		String result = "";
		double area = polygonArea(x, y);		
		int n = x.length;
		double cx = 0;
		double cy = 0;
		for (int i = 0; i < n; i++) {
			cx += (x[i % n] + x[(i + 1) % n])
					* (x[i % n] * y[(i + 1) % n] - x[(i + 1) % n] * y[i % n]);
			cy += (y[i % n] + y[(i + 1) % n])
					* (x[i % n] * y[(i + 1) % n] - x[(i + 1) % n] * y[i % n]);
		}
		cx *= 1 / (6 * area);
		cy *= 1 / (6 * area);
		
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		
		String cxStr = twoDForm.format(cx);
		String cyStr = twoDForm.format(cy);
		
		if(cxStr.indexOf(".") == -1)
			cxStr = cxStr.concat(".00");
		else if(cxStr.indexOf(".") == cxStr.length()-2)
			cxStr = cxStr.concat("0");
		
		if(cyStr.indexOf(".") == -1)
			cyStr = cyStr.concat(".00");
		else if(cyStr.indexOf(".") == cyStr.length()-2)
			cyStr = cyStr.concat("0");
		result = cyStr + " " + cyStr;
		return result;
	}

}
