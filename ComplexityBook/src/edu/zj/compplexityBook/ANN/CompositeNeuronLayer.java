package edu.zj.compplexityBook.ANN;

public class CompositeNeuronLayer implements INeuronLayer {
	private NeuronLayer[] neuronLayers;
	private int outputSize;
	private int inputSize;
	public CompositeNeuronLayer(int inputSize,NeuronLayer[] neuronLayers) throws InputSizeIncorrectExeception {
		this.neuronLayers = neuronLayers;
		this.inputSize=inputSize;
		outputSize = 0;
		for (int i = 0; i < neuronLayers.length; i++) {
			outputSize += neuronLayers[i].getOutputSize();
			if (neuronLayers[i].getInputSize() != inputSize) {
				throw new InputSizeIncorrectExeception(
						"input length = " + inputSize + " layer input size= " + neuronLayers[i].getInputSize());
			}

		}
	}

	@Override
	public double[] output(double input[]) throws InputSizeIncorrectExeception {
		double[] output = new double[outputSize];
		double[] layerOutput;
		int counter=0;
		for (int i = 0; i < neuronLayers.length; i++) {
			layerOutput = neuronLayers[i].output(input);
			for (int j=0;j<layerOutput.length;j++) {
				output[counter++]=layerOutput[j];
			}
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
		return outputSize;
	}

}
