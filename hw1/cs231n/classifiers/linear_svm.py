import numpy as np
from random import shuffle
from past.builtins import xrange

def svm_loss_naive(W, X, y, reg):
  """
  Structured SVM loss function, naive implementation (with loops).

  Inputs have dimension D, there are C classes, and we operate on minibatches
  of N examples.

  Inputs:
  - W: A numpy array of shape (D, C) containing weights.
  - X: A numpy array of shape (N, D) containing a minibatch of data.
  - y: A numpy array of shape (N,) containing training labels; y[i] = c means
    that X[i] has label c, where 0 <= c < C.
  - reg: (float) regularization strength

  Returns a tuple of:
  - loss as single float
  - gradient with respect to weights W; an array of same shape as W
  """
  dW = np.zeros(W.shape) # initialize the gradient as zero

  # compute the loss and the gradient
  num_classes = W.shape[1]
  num_train = X.shape[0]
  loss = 0.0
  for i in xrange(num_train):
    scores = X[i].dot(W)
    correct_class_score = scores[y[i]]
    for j in xrange(num_classes):
      if j == y[i]:
        continue
      margin = scores[j] - correct_class_score + 1 # note delta = 1
      if margin > 0:
        loss += margin
        #if margin>0, we have 2 partial derivatives: with respect to w(j) and with respect to w(yi)
        dW[:,j] += X[i]
        dW[:,y[i]] -= X[i]

  # Right now the loss is a sum over all training examples, but we want it
  # to be an average instead so we divide by num_train.
  loss /= num_train

  # Add regularization to the loss.
  loss += reg * np.sum(W * W)

  # Add regularization gradient to the gradient
  dW /= X.shape[0]
  dW += 2 * W * reg

  #############################################################################
  # TODO:                                                                     #
  # Compute the gradient of the loss function and store it dW.                #
  # Rather that first computing the loss and then computing the derivative,   #
  # it may be simpler to compute the derivative at the same time that the     #
  # loss is being computed. As a result you may need to modify some of the    #
  # code above to compute the gradient.                                       #
  #############################################################################
  np.savetxt('c:/work/data/transfer/naive_loss.csv',dW,delimiter=',')
  return loss, dW

def svm_loss_vectorized(W, X, y, reg):
  """
  Structured SVM loss function, vectorized implementation.

  Inputs and outputs are the same as svm_loss_naive.
  """
  loss = 0.0
  dW = np.zeros(W.shape) # initialize the gradient as zero

  #############################################################################
  # TODO:                                                                     #
  # Implement a vectorized version of the structured SVM loss, storing the    #
  # result in loss.                                                           #
  #############################################################################
  N=X.shape[0]
  scores = np.matmul(X,W)
  true_category_scores = scores[np.arange(N), y].reshape((N,1))
  diffs = scores - true_category_scores + 1
  diffs[diffs < 0] = 0
  diffs[np.arange(N),y] = 0 #do not count the diffs for the category = true category
  loss = np.sum(diffs)
  loss /= N

  #############################################################################
  # TODO:                                                                     #
  # Implement a vectorized version of the gradient for the structured SVM     #
  # loss, storing the result in dW.                                           #
  #                                                                           #
  # Hint: Instead of computing the gradient from scratch, it may be easier    #
  # to reuse some of the intermediate values that you used to compute the     #
  # loss.                                                                     #
  #############################################################################
  #for every non-null element [i,j] in the diffs matrix, add the i'th row of the
  #X matrix to j'th column of the dW matrix
  positive_diffs_indx = np.where(diffs > 0)

  #for each [example,category] in the positive_diffs_indx, add the xi's to the d'weights for that category
  dW[:,positive_diffs_indx[1]] += np.transpose(X[positive_diffs_indx[0],:])

  #for each [example,...] in the positive_diffs_indx, substract the xi's from the d'weights of the true category
  true_categories_for_dw = y[positive_diffs_indx[0]]
  dW[:,true_categories_for_dw] -= np.transpose(X[positive_diffs_indx[0],:])

  dW /= N
  dW += 2 * W * reg
  np.savetxt('c:/work/data/transfer/vectorized_loss.csv',dW,delimiter=',')
  return loss, dW
