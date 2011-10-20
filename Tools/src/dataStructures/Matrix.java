package dataStructures;

public class Matrix {
	int[][] data;

	public Matrix(int[][] data) {
		this.data = data;
	}

	public Matrix(int rows, int cols) {
		this.data = new int[rows][cols];
	}

	public static Matrix build(int numRows, int numCols, double factor) {
		int[][] data = new int[numRows][numCols];
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[0].length; col++) {
				data[row][col] = Math.random() > factor ? 1 : 0;
			}
		}
		return new Matrix(data);
	}

	public int get(int row, int col) {
		return data[row][col];
	}

	public boolean getBoolean(int row, int col) {
		return data[row][col] > 0;
	}

	public void set(int row, int col, int value) {
		data[row][col] = value;
	}

	public void setBoolean(int row, int col, boolean value) {
		data[row][col] = value ? 1 : 0;
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

	/*
	 * M[i][k] = M[i][k] || (M[i][j] && M[j][k]) A square adjacency matrix is
	 * assumed
	 */
	public boolean isConnected() {
		Matrix tempMatrix = new Matrix(data.length, data[0].length);
		boolean someChange = false;
		do {
			someChange = false;
			for (int row = 0; row < data.length; row++) {
				for (int col = 0; col < data[0].length; col++) {
					for (int pointer = 0; pointer < data.length; pointer++) {
						boolean origValue = tempMatrix.getBoolean(row, col);
						boolean newValue = tempMatrix.getBoolean(row, col)
								|| (this.getBoolean(row, pointer) && this
										.getBoolean(pointer, col));
						if (origValue != newValue) {
							someChange = true;
							tempMatrix.setBoolean(row, col, newValue);
						}
					}
				}
			}
		} while (someChange);
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[0].length; col++) {
				if (!tempMatrix.getBoolean(row, col))
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Matrix matrix = Matrix.build(5, 5, 0.5);
		System.out.println(matrix);
	}
}
