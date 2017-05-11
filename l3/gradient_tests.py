import numpy as np
import hw1.cs231n.data_utils as utils
import unittest
import hw1.cs231n.gradient_check as gradient
from l3.loss_functions import CIFAR10_loss_fun


class GradientTests(unittest.TestCase):

    def testNumericGradient(self):
        W = np.random.rand(10,3072) * 0.001
        df = gradient.eval_numerical_gradient(CIFAR10_loss_fun,W)