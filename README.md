# IA

This project includes some artificial intelligence code in both Java and JavaScript. It also includes some random experiments.

On the artificial intelligence Java part in the main project you will find:

	- Some libraries:
		- genetic --> An implementation of a genetic algorithm
		- neuron --> An implementation of an artificial neuron which can be reproduced using a genetic algorithm
		- mlp --> An implementation of a multi-layer perceptron which can be reproduced using a genetic algorithm
		- lstm --> A long short term memory neuron
	- Some code examples:
		- geneticlstm --> A long short term memory neuron trained using a genetic algorithm which can be used to predict a text pattern
		- geneticmlpcar --> A simple multi-layer perceptron 2D car which is trained using a genetic algorithm to drive inside a circuit
		- [WORK IN PROGRESS] convolutionalgeneticmlpimageclassifier --> An experiment to classify cifar images using a convolutional network trained with a genetic algorithm

In Javascript the most interesting artificial intelligence examples are:

    - perceptron --> A perceptron which is trained to solve the problem of values greater than/lower than
    - mlp --> A multi-layer perceptron that can be trained using backpropagation (https://en.wikipedia.org/wiki/Backpropagation) and it solves the XOR problem