package edu.zj.compplexityBook.CA;

public class CADataBounded<T extends Enum<T>> implements CAData<T> {

	private final Object[][] data;

	public CADataBounded(int rowCount,int ColumnCount) {
		this.data=new Object[rowCount][ColumnCount];
	}
	public CADataBounded(CADataBounded<T> ca) {
		this(ca.data.length,ca.data[0].length);
		for (int i=0;i<data.length;i++) {
			for (int j=0;j<data[0].length;j++) {
				this.data[i][j]=ca.data[i][j];
			}
			
		}
	}

	public int getRowSize() {
		return data.length;
	}

	public int getColumnSize() {
		return data[0].length;
	}

	@SuppressWarnings("unchecked")
	public T getData(int row, int column) {
		return (T) data[row][column];
	}

	public void setData(T data, int row, int column) {
		this.data[row][column]=data;
	}
	@Override
	public void setData(T data, String row, String column) {
		setData(data,Integer.parseInt(row),Integer.parseInt(column));
	}
	@Override
	public T getData(String row, String column) {
		// TODO Auto-generated method stub
		return getData(Integer.parseInt(row),Integer.parseInt(column));
	}

}
