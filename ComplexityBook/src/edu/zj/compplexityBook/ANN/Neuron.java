package edu.zj.compplexityBook.ANN;

import java.util.function.DoubleFunction;

public class Neuron {
	private ArtificialNeuralNetwork ann;
	private NeuronLayer neuronLayer;

	public NeuronLayer getNeuronLayer() {
		return neuronLayer;
	}

	public void setNeuronLayer(NeuronLayer neuronLayer) {
		this.neuronLayer = neuronLayer;
	}

	private int inputSize;

	public int getInputSize() {
		return inputSize;
	}

	private double weight[];
	private double bias;
	private DoubleFunction<Double> activationFunction;

	public Neuron(double weight[], double bias, DoubleFunction<Double> activationFunction) {
		this.inputSize = weight.length;
		this.bias = bias;
		this.weight = weight;
		this.activationFunction = activationFunction;
	}

	public static DoubleFunction<Double> hardlim = v -> {
		if (v < 0)
			return 0d;
		return 1d;
	};
	public static DoubleFunction<Double> hardlims = v -> {
		if (v < 0)
			return -1d;
		return 1d;
	};
	public static DoubleFunction<Double> purelin = v -> {
		return v;
	};
	public static DoubleFunction<Double> satlin = v -> {
		if (v < 0)
			return 0d;
		if (v > 1)
			return 1d;
		return v;
	};
	public static DoubleFunction<Double> satlins = v -> {
		if (v < -1)
			return -1d;
		if (v > 1)
			return 1d;
		return v;
	};

	public static DoubleFunction<Double> logsig = v -> {
		return 1 / (1 + Math.exp(-v));
	};

	public static DoubleFunction<Double> tansig = v -> {
		return (Math.exp(v) - Math.exp(-v)) / (Math.exp(v) + Math.exp(-v));
	};

	public static DoubleFunction<Double> poslin = v -> {
		if (v < 0)
			return 0d;
		return v;
	};
	public DoubleFunction<Double> compet = v -> {
		// for euals ???
		if (v == ann.max_n())
			return 1d;
		return 0d;
	};

	public double output(double[] input) throws InputSizeIncorrectException {
		if (input.length != inputSize) {
			throw new InputSizeIncorrectException("input length = " + input.length + " inputSize= " + inputSize);
		}
		double n = bias;
		for (int i = 0; i < inputSize; i++) {
			n += weight[i] * input[i];
		}
		return activationFunction.apply(n);
	}
}
