package edu.zj.complexityBook.ANN;

public interface INeuronLayer {
	public double[] output(double input[]) throws InputSizeIncorrectException;
	public int getInputSize();
	public int getOutputSize();

}
