package misc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StocksAnalysis {
	public static void main(String[] args) throws Exception {
		String[] input = IOUtils.fileToArray("misc" + File.separator
				+ "stock-quotes.txt");
		List<ValueWithId> completeList = new ArrayList<ValueWithId>();
		for (String s : input) {
			String[] splits = s.split(" \\| ");
			String company = splits[0];
			String companyCode = splits[1];
			String[] quotesStr = splits[2].substring(1, splits[2].length() - 1)
					.split(", ");
			double[] quotesDbl = DSUtils.stringArrayToDoubleArray(quotesStr);
			double[][] quotesTable = MathUtils.relativeIncrease(quotesDbl);
			for (int i = 0; i < quotesTable.length; i++) {
				for (int j = 0; j < quotesTable[i].length; j++) {
					if (j > i)
						completeList.add(new ValueWithId(companyCode + "-" + i
								+ "-" + j, quotesTable[i][j]));
				}
			}
		}

		ValueWithId[] inputArray = (ValueWithId[]) completeList
				.toArray(new ValueWithId[completeList.size()]);
		AlgoUtils.quickSort(inputArray);
		IOUtils.arrayToFile(inputArray, "C:\\temp\\stock-analysis-output.txt");
	}
}
