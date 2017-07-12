package edu.zj.compplexityBook.ANN;

public class ArtificialNeuralNetwork implements INeuronLayer {
	private INeuronLayer[] neuroLayers;
	private int inputSize;
	private int outputSize;

	public ArtificialNeuralNetwork(int inputSize, INeuronLayer[] neuroLayers) throws InputSizeIncorrectExeception {
		this.inputSize = inputSize;
		this.neuroLayers = neuroLayers;

		outputSize = inputSize;

		for (int i = 0; i < neuroLayers.length; i++) {
			if (neuroLayers[i].getInputSize() != outputSize) {
				throw new InputSizeIncorrectExeception(
						"input length = " + outputSize + " layer input size= " + neuroLayers[i].getInputSize());
			}
			outputSize = neuroLayers[i].getOutputSize();
		}
	}

	public double max_n() {
		return 0d;
	}

	@Override
	public double[] output(double[] input) throws InputSizeIncorrectExeception {
		double[] output = null;
		for (int i = 0; i < neuroLayers.length; i++) {
			output = neuroLayers[i].output(input);
			input = output;
		}
		return output;
	}

	@Override
	public int getInputSize() {
		// TODO Auto-generated method stub
		return inputSize;
	}

	@Override
	public int getOutputSize() {
		// TODO Auto-generated method stub
		return outputSize;
	};
}
