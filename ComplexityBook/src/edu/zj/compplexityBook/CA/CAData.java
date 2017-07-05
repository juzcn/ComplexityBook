package edu.zj.compplexityBook.CA;

public interface CAData<T extends Enum<T>>  {
	public T getData(String row, String column);
	public void setData(T data, String row, String column);
	public int getRowSize();
	public int getColumnSize();

}
