import unittest
import data_utils as utils
import classifiers.linear_svm as svm
import numpy as np


def loadCifar(num_training = 500, num_test = 50):
    cifar10_dir = '../../data/cifar10'
    X_train, y_train, X_test, y_test = utils.load_CIFAR10(cifar10_dir)

    mask = list(range(num_training))
    X_train = X_train[mask]
    y_train = y_train[mask]

    mask = list(range(num_test))
    X_test = X_test[mask]
    y_test = y_test[mask]

    X_train = np.reshape(X_train, (X_train.shape[0], -1))
    X_test = np.reshape(X_test, (X_test.shape[0], -1))
    mean_image = np.mean(X_train, axis=0)
    X_train -= mean_image
    X_test -= mean_image

    X_train = np.hstack([X_train, np.ones((X_train.shape[0], 1))])
    X_test = np.hstack([X_test, np.ones((X_test.shape[0], 1))])
    print(X_train.shape, X_test.shape)
    return X_train, y_train, X_test, y_test

class ClassifiersTest(unittest.TestCase):

    def test_vectorized_svm(self):
        X_train, y_train, X_test, y_test = loadCifar(100,10)
        W = np.random.randn(3073, 10) * 0.0001
        loss, gradient = svm.svm_loss_vectorized(W,X_train, y_train,1)
        print(loss, gradient)
