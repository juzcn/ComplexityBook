package edu.zj.compplexityBook.ANN;

import java.util.function.DoubleFunction;

public class Neuron {
	private ArtificialNeuralNetwork ann;
	private int inputSize;
	public int getInputSize() {
		return inputSize;
	}

	private double weight[];
	private double bias;
	private DoubleFunction<Double> activationFunction;
	private double precOutput;
	private boolean delayed = false;

	public Neuron(ArtificialNeuralNetwork ann, double weight[], double bias,
			DoubleFunction<Double> activationFunction) {
		this.ann = ann;
		this.inputSize = weight.length;
		this.bias = bias;
		this.weight = weight;
		this.activationFunction = activationFunction;
	}

	public Neuron(ArtificialNeuralNetwork ann, double weight[], double bias, DoubleFunction<Double> activationFunction,
			double initialOutput) {
		// delay neuron
		this(ann, weight, bias, activationFunction);
		this.precOutput = initialOutput;
		this.delayed = true;
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

	public double output(double[] input) throws InputSizeIncorrectExeception {
		if (input.length != inputSize) {
			throw new InputSizeIncorrectExeception("input length = " + input.length + " inputSize= " + inputSize);
		}
		double n = bias;
		for (int i = 0; i < inputSize; i++) {
			n += weight[i] * input[i];
		}
		double output = activationFunction.apply(n);
		if (delayed) {
			double exchange = output;
			output = precOutput;
			precOutput = exchange;
		}
		return output;
	}
}
