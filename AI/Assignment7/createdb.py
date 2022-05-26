import torch
import numpy as np

MIN_VALUE = -10
MAX_VALUE = 10
PARAMS_NUMBER = 2

class Dataset:
    @staticmethod
    def createDistribution():
        return (MAX_VALUE - MIN_VALUE) * torch.rand(1000, PARAMS_NUMBER) + MIN_VALUE

    @staticmethod
    def computeFunctionValue(x1, x2):
        return np.sin(x1 + x2/np.pi)

    @staticmethod
    def computeFunctionAtEachPoint(points):
        return torch.tensor(
            [Dataset.computeFunctionValue(point[0], point[1]) for point in points.numpy()]
        )

    @staticmethod
    def createPairs():
        distribution = Dataset.createDistribution()
        
        return torch.column_stack(
            (distribution,
            Dataset.computeFunctionAtEachPoint(distribution))
        )

    @staticmethod
    def saveToFile():
        torch.save(Dataset.createPairs(), 'mydataset.dat')


Dataset.saveToFile()