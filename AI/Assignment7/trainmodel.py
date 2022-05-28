
import torch
import torch.nn.functional as F
import matplotlib.pyplot as plt
import numpy as np

import myModel

class TrainModel:
    def __init__(self):
        self.lossFunction = torch.nn.MSELoss()
        self.neuralNetwork = myModel.Net(2, 20, 1).double()
        # we use an optimizer that implements stochastic gradient descent 
        self.optimizerBatch = torch.optim.SGD(self.neuralNetwork.parameters(), lr=0.005)
 
        tensors = torch.load('mydataset.dat')
        self.inputTensor = tensors.narrow(1, 0, 2)
        self.outputTensor = tensors.narrow(1, 2, 1)

    def train(self):
        losses = []
        averageLosses = []

        batchSize = 20
        epochs = 3000
        batches = int(1000 / batchSize)

        splitInputData = torch.split(self.inputTensor, batchSize)
        splitOutputData = torch.split(self.outputTensor, batchSize)
        
        for epoch in range(epochs):
            lossSum = 0
            for batch in range(batches):
                predictedOutput = self.neuralNetwork(splitInputData[batch].double())

                loss = self.lossFunction(predictedOutput, splitOutputData[batch])

                losses.append(loss)

                lossSum += loss.item()

                self.optimizerBatch.zero_grad()

                loss.backward()

                self.optimizerBatch.step()

            averageLosses.append(lossSum / batches)

            if epoch % 100 == 99:
                predicted_values = self.neuralNetwork(self.inputTensor.double())

                loss = self.lossFunction(predicted_values, self.outputTensor)
                print('\r epoch: {}\tLoss = {:.5}'.format(epoch, loss))

        plt.plot(averageLosses)
        plt.show()

    def save(self):
        torch.save(self.neuralNetwork.state_dict(), 'myNetwork.pt')

model = TrainModel()
model.train()
model.save()