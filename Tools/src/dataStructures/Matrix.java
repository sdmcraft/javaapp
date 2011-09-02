package dataStructures;

public class Matrix {
	boolean[][] data;

	public Matrix(boolean[][] data) {
		this.data = data;
	}

	public static Matrix build(int numRows, int numCols) {
		boolean[][] data = new boolean[numRows][numCols];
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[0].length; col++) {
				data[row][col] = Math.random() * 100 > 70;
			}
		}
		return new Matrix(data);
	}

	public boolean get(int row, int col) {
		return data[row][col];
	}

	public void set(int row, int col, boolean value) {
		data[row][col] = value;
	}

	public int numRows() {
		return data.length;
	}

	public int numCols() {
		return data[0].length;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[0].length; col++) {
				sb.append(data[row][col]).append("\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Matrix matrix = Matrix.build(5, 5);
		System.out.println(matrix);
	}
}
