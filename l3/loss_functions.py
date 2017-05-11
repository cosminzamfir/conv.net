import numpy as np
import hw1.cs231n.data_utils as utils

def CIFAR10_loss_fun(W):
    print('Invoke loss computation')
    Xtr, Ytr, Xte, Yte = utils.load_CIFAR10('../../data/cifar10/')
    Xtr = Xtr.reshape(Xtr.shape[0], 32 * 32 * 3)  # Xtr_rows becomes 50000 x 3072
    Xtr = np.transpose(Xtr)
    printShape(Xtr, 'X training')
    printShape(Ytr, 'Y training')

    # ones = np.ones(Xtr.shape[1])
    # print(ones.shape)
    # print(Xtr.shape)
    # Xtr = np.vstack((Xtr, ones))

    res = np.zeros(Ytr.shape[0])
    for i in range(Ytr.shape[0]) :
        X = Xtr[:,i]
        res[i] = np.sum(np.dot(W,X) - np.dot(W[Ytr[i],:], X) + 1) - 1
    return np.mean(res)

def printShape(a, name):
    if  len(a.shape) == 2:
        print(name, ':', a.shape[0], ' rows; ', a.shape[1], ' columns')
    else:
        print(name, ':', a.shape[0], ' elements')
