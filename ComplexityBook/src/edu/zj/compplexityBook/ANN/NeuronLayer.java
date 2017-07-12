package edu.zj.compplexityBook.ANN;

public class NeuronLayer implements INeuronLayer {
	private int inputSize;
	private Neuron[] neurons;

	public NeuronLayer(int inputSize, Neuron... neurons) throws InputSizeIncorrectExeception {
		for (int i = 0; i < neurons.length; i++) {
			if (neurons[i].getInputSize() != inputSize) {
				throw new InputSizeIncorrectExeception(
						"input length = " + inputSize + " neuron input size= " + neurons[i].getInputSize());
			}
		}
		this.neurons = neurons;
		this.inputSize = inputSize;
	}

	public int getOutputSize() {
		return neurons.length;
	}

	@Override
	public double[] output(double input[]) throws InputSizeIncorrectExeception {
		if (input.length != inputSize) {
			throw new InputSizeIncorrectExeception("input length = " + input.length + " inputSize= " + inputSize);
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
