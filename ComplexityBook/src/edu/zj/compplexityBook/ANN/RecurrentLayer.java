package edu.zj.compplexityBook.ANN;

public class RecurrentLayer extends NeuronLayer {
	public RecurrentLayer(int inputSize, Neuron... neurons) throws InputSizeIncorrectException {
		super(inputSize, neurons);
		if (inputSize != neurons.length) {
			throw new InputSizeIncorrectException("input length = " + inputSize + " neuron size= " + neurons.length);
		}
	}

	public static int MAX_ITERATIONS = 100;
	public static double EPSILON = 0.001;

	public static boolean equals(double a1[], double a2[]) {
		if (a1.length != a2.length)
			return false;
		for (int i = 0; i < a1.length; i++) {
			if (!(a1[i] < a2[i] + EPSILON && a1[i] > a2[i] - EPSILON))
				return false;
		}
		return true;
	}

	@Override
	public double[] output(double[] initialCondition) throws InputSizeIncorrectException {
		if (getInputSize() != initialCondition.length) {
			throw new InputSizeIncorrectException(
					"input length = " + getInputSize() + " initial size= " + initialCondition.length);
		}
		double[] input = initialCondition;
		double[] output;
		int iteration = 0;
		while (iteration < MAX_ITERATIONS) {
			iteration++;
			output = super.output(input);
//			System.out.println("input " + input.length + " input_0 " + input[0] + " input_1 " + input[1]);
//			System.out.println("output " + output.length + " output_0 " + output[0] + " output_1 " + output[1]);
			if (equals(input,output)) {
				System.out.println("Converge at " + iteration);
				return output;
			}
			input = output;
		}
		System.out.println("Not converge " + iteration);
		return null;
	}

}
