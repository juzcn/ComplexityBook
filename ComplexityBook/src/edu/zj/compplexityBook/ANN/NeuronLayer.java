package edu.zj.compplexityBook.ANN;

public class NeuronLayer implements INeuronLayer {
	private int inputSize;
	private Neuron[] neurons;

	public NeuronLayer(int inputSize, Neuron... neurons) throws InputSizeIncorrectException {
		for (int i = 0; i < neurons.length; i++) {
			if (neurons[i].getInputSize() != inputSize) {
				throw new InputSizeIncorrectException(
						"input length = " + inputSize + " neuron input size= " + neurons[i].getInputSize());
			}
			neurons[i].setNeuronLayer(this);
		}
		this.neurons = neurons;
		this.inputSize = inputSize;
	}

	public int getOutputSize() {
		return neurons.length;
	}

	@Override
	public double[] output(double input[]) throws InputSizeIncorrectException {
		if (input.length != inputSize) {
			throw new InputSizeIncorrectException("input length = " + input.length + " inputSize= " + inputSize);
		}
		double[] output = new double[neurons.length];
		for (int i = 0; i < neurons.length; i++) {
			output[i] = neurons[i].output(input);
		}
		return output;
	}

	@Override
	public int getInputSize() {
		return inputSize;
	}
	
}
