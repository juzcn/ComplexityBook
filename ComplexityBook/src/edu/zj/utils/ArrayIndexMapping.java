package edu.zj.utils;

public class ArrayIndexMapping {
	private int[] dimensions;

	public ArrayIndexMapping(int[] dimensions) {
		this.dimensions = dimensions;
	}

	public final int[] indexMapping(int index) {
		int m[] = new int[dimensions.length];
		for (int i = 0; i < dimensions.length; i++) {
			m[i] = index % dimensions[i];
			index = index / dimensions[i];
		}
		return m;
	}

	private final int multiplier(int index, int[] d) {
		int m = 1;
		for (int i = 0; i < index; i++) {
			m *= d[i];
		}
		return m;
	}

	public final int indexMapping(int[] index) {
		int m = 0;
		for (int i = 0; i < index.length; i++) {
			m += index[i] * multiplier(i, dimensions);
		}
		return m;
	}

	public static void main(String[] args) {
		int d[] = { 2, 3, 3, 3 };
		ArrayIndexMapping aim = new ArrayIndexMapping(d);
		byte[][][][] d3 = new byte[d[0]][d[1]][d[2]][d[3]];
		int size = d[0] * d[1] * d[2] * d[3];
		byte[] d1 = new byte[size];

		for (byte i = 0; i < size; i++) {
			d1[i] = i;
			System.out.println(" i= " + i + " value " + d1[i]);
		}
		for (int i = 0; i < size; i++) {
			int m[] = aim.indexMapping(i);
			d3[m[0]][m[1]][m[2]][m[3]] = d1[i];
		}

		int s;
		for (int i = 0; i < d[0]; i++) {
			for (int j = 0; j < d[1]; j++) {
				for (int k = 0; k < d[2]; k++) {
					for (int l = 0; l < d[3]; l++) {
						int[] index = new int[4];
						index[0] = i;
						index[1] = j;
						index[2] = k;
						index[3] = l;
						s = aim.indexMapping(index);
						// s = i * d[1] * d[2] + j * d[2] + k;
						System.out.println(" s= " + s + " value " + d3[i][j][k][l]);
					}
				}

			}

		}
	}
}
