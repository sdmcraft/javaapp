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

	public static Matrix cycleFreeBuild(int numRows, int numCols, double factor) {
		int[][] data = new int[numRows][numCols];
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[0].length; col++) {
				if (row == col)
					data[row][col] = 0;
				else if (data[row][col] == -1)
					data[row][col] = 0;
				else {
					data[row][col] = Math.random() > factor ? 1 : 0;
					if (data[row][col] == 1)
						data[col][row] = -1;
				}
			}
		}
		return new Matrix(data);
	}

	public static Matrix buildConected(int numRows, int numCols, double factor) {
		Matrix matrix;
		while (!(matrix = Matrix.cycleFreeBuild(numRows, numCols, factor))
				.isConnected())
			;
		return matrix;
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

	public Matrix clone() {
		Matrix tempMatrix = new Matrix(data.length, data[0].length);
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[0].length; col++) {
				tempMatrix.set(row, col, this.get(row, col));
			}
		}
		return tempMatrix;
	}

	/*
	 * M[i][k] = M[i][k] || M[k][i] || (M[i][j] && M[j][k]) A square adjacency
	 * matrix is assumed
	 */
	public boolean isConnected() {
		Matrix tempMatrix = this.clone();
		boolean someChange = false;
		do {
			someChange = false;
			for (int row = 0; row < data.length; row++) {
				for (int col = 0; col < data[0].length; col++) {
					boolean origValue = tempMatrix.getBoolean(row, col);
					for (int pointer = 0; pointer < data.length; pointer++) {
						boolean newValue = tempMatrix.getBoolean(row, col)
								|| tempMatrix.getBoolean(col, row)
								|| (tempMatrix.getBoolean(row, pointer) && tempMatrix
										.getBoolean(pointer, col));
						if (!origValue && newValue) {
							someChange = true;
							tempMatrix.setBoolean(row, col, newValue);
							break;
						}
					}
				}
			}
		} while (someChange);
		System.out.println(tempMatrix);
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[0].length; col++) {
				if (!tempMatrix.getBoolean(row, col)) {
					System.out.println("Connected:false");
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Matrix matrix = Matrix.build(5, 5, 0.5);
		System.out.println(matrix);
		System.out.println(matrix.isConnected());
	}
}
