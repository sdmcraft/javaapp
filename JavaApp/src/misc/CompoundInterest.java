package misc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CompoundInterest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader reader = null;		
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Initial Value:");
			double p = Double.parseDouble(reader.readLine());
			System.out.print("Annual Interest Rate(%):");
			double r = Double.parseDouble(reader.readLine()) / 100;
			System.out
					.print("Number of times the interest is compounded per year:");
			double n = Double.parseDouble(reader.readLine());
			System.out.print("Time elapsed(in days):");
			double t = Double.parseDouble(reader.readLine()) / 365;
			double amt = p * Math.pow((1 + r / n), n * t);
			System.out.print("Projected Value:" + amt);
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

}
