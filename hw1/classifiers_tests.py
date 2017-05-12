import unittest
import datasets
import classifiers.linear_svm

def loadCifar(num_training = 500, num_test = 50):
    cifar10_dir = '../../data/cifar10'
    X_train, y_train, X_test, y_test = datasets.load_CIFAR10(cifar10_dir)

    mask = list(range(num_training))
    X_train = X_train[mask]
    y_train = y_train[mask]

    mask = list(range(num_test))
    X_test = X_test[mask]
    y_test = y_test[mask]


class ClassifiersTest(unittest.TestCase):
