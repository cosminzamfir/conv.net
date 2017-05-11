import hw1.cs231n.classifiers.k_nearest_neighbor
import hw1.cs231n.data_utils as datautils
import unittest
import numpy as np
import matplotlib.pyplot as plt
from l2.nearest_neighbour import NearestNeighbour
from l2.k_nearest_neighbour import KNearestNeighbour

class NearestNeighbourTest(unittest.TestCase):

    def testNearestNeighbour(self):
        Xtr, Ytr, Xte, Yte = datautils.load_CIFAR10('../../data/cifar10/')
        Xtr_rows = Xtr.reshape(Xtr.shape[0], 32 * 32 * 3)  # Xtr_rows becomes 50000 x 3072
        Xte_rows = Xte.reshape(Xte.shape[0], 32 * 32 * 3)  # Xte_rows becomes 10000 x 3072
        Xte_rows = Xte_rows[0:20,:]
        Yte = Yte[0:20]
        nn = NearestNeighbour()
        nn.train(Xtr_rows, Ytr)
        Yte_predict = nn.predict(Xte_rows)
        print('accuracy: %f' % (np.mean(Yte_predict == Yte)))
        assert np.mean(Yte_predict == Yte) > 0.1

    def testKNearestNeightbour(self):
        test_images = 10
        validation_accuracies = []
        Xtr, Ytr, Xte, Yte = datautils.load_CIFAR10('../../data/cifar10/')
        Xtr_rows = Xtr.reshape(Xtr.shape[0], 32 * 32 * 3)  # Xtr_rows becomes 50000 x 3072
        Xte_rows = Xte.reshape(Xte.shape[0], 32 * 32 * 3)  # Xte_rows becomes 10000 x 3072
        Xte_rows = Xte_rows[0:test_images,:]
        Yte = Yte[0:test_images]
        nn = KNearestNeighbour()
        nn.train(Xtr_rows, Ytr)
        for k in [1,3,5,7,10,20,50]:
            Yte_predict = nn.predict(Xte_rows,k)
            acc = np.mean(Yte_predict == Yte)
            print('accuracy: %f' % acc)
            validation_accuracies.append(acc)
        plt.bar(validation_accuracies,height=10)
