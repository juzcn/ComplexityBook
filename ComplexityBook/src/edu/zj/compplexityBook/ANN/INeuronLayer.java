package edu.zj.compplexityBook.ANN;

public interface INeuronLayer {
	public double[] output(double input[]) throws InputSizeIncorrectExeception;
	public int getInputSize();
	public int getOutputSize();

}
