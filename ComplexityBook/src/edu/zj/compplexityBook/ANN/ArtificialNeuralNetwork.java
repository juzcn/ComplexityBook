package edu.zj.compplexityBook.ANN;

public class ArtificialNeuralNetwork implements INeuronLayer {
	private INeuronLayer[] neuroLayers;
	private int inputSize;
	private int outputSize;

	public ArtificialNeuralNetwork(int inputSize, INeuronLayer... neuroLayers) throws InputSizeIncorrectException {
		this.inputSize = inputSize;
		this.neuroLayers = neuroLayers;

		outputSize = inputSize;

		for (int i = 0; i < neuroLayers.length; i++) {
			if (neuroLayers[i].getInputSize() != outputSize) {
				throw new InputSizeIncorrectException(
						"input length = " + outputSize + " layer input size= " + neuroLayers[i].getInputSize());
			}
			outputSize = neuroLayers[i].getOutputSize();
		}
	}

	public double max_n() {
		return 0d;
	}

	@Override
	public double[] output(double[] input) throws InputSizeIncorrectException {
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

	public static void oneNeuronDemo() throws InputSizeIncorrectException {
		Neuron neuron = new Neuron(new double[] { 0d, 1d, 0d }, 0d, Neuron.hardlims);
		NeuronLayer neuronLayer = new NeuronLayer(3, neuron);
		ArtificialNeuralNetwork ann = new ArtificialNeuralNetwork(3, neuronLayer);

		double[] output = ann.output(new double[] { 1d, -1d, -1d });
		System.out.println("output size " + output.length);
		System.out.println("output value " + output[0]);

		output = ann.output(new double[] { 1d, 1d, -1d });
		System.out.println("output size " + output.length);
		System.out.println("output value " + output[0]);

	}

	public static void print(double[] output) {
		System.out.print("Array length:" + output.length);
		for (int i = 0; i < output.length; i++) {
			System.out.print(" " + output[i]);
		}
		System.out.println();
	}

	public static void HopfieldNetworkDemo() throws InputSizeIncorrectException {
		double[] orange = { 1d, -1d, -1d };
		double[] apple = { 1d, 1d, -1d };

		Neuron neuron1 = new Neuron(new double[] { 0.2d, 0d, 0d }, 0.9d, Neuron.satlins);
		Neuron neuron2 = new Neuron(new double[] { 0d, 1.2d, 0d }, 0d, Neuron.satlins);
		Neuron neuron3 = new Neuron(new double[] { 0d, 0d, 0.2d }, -0.9d, Neuron.satlins);

		RecurrentLayer recurrentLayer = new RecurrentLayer(3, neuron1, neuron2, neuron3);

		ArtificialNeuralNetwork ann = new ArtificialNeuralNetwork(3, recurrentLayer);

		double[] output = ann.output(orange);
		print(output);
		output = ann.output(apple);
		print(output);
	}

	public static void HammingNetworkDemo() throws InputSizeIncorrectException {
		double[] orange = { 1d, -1d, -1d };
		double[] apple = { 1d, 1d, -1d };

		Neuron neuron1 = new Neuron(new double[] { 1d, -1d, -1d }, 3d, Neuron.purelin);
		Neuron neuron2 = new Neuron(new double[] { 1d, 1d, -1d }, 3d, Neuron.purelin);
		NeuronLayer feedforwardLayer = new NeuronLayer(3, neuron1, neuron2);

		neuron1 = new Neuron(new double[] { 1d, -0.5d }, 0d, Neuron.poslin);
		neuron2 = new Neuron(new double[] { -0.5d, 1d }, 0d, Neuron.poslin);
		RecurrentLayer recurrentLayer = new RecurrentLayer(2, neuron1, neuron2);

		ArtificialNeuralNetwork ann = new ArtificialNeuralNetwork(3, feedforwardLayer, recurrentLayer);

		double[] output = ann.output(orange);
		print(output);
		output = ann.output(apple);
		print(output);
	}

	public static void main(String[] args) throws InputSizeIncorrectException {
		// oneNeuronDemo();
		// HammingNetworkDemo();
		HopfieldNetworkDemo();
	}
}
