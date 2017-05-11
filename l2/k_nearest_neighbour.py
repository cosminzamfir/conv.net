import numpy as np
import scipy.stats as stats


class KNearestNeighbour():
    def train(self, Xtr_rows, Ytr):
        self.Xtr_rows = Xtr_rows
        self.Ytr = Ytr

    def predict(self, Xte_rows,k):
        #the train data is an array of size numTrainingExample x trainingArraySize. in our case: 50,000 x 3072
        #each row of Xte_rows is an array of size 3072
        #compute the sumb of abs difference between test row and each row of train array as an 50,000 elements array
        num_tests = Xte_rows.shape[0]
        res = np.zeros(num_tests, dtype = self.Ytr.dtype) # the result is an array with the best classification for each example
        for test_index in range(num_tests):
            test_row = Xte_rows[test_index,:]
            distances = np.sum(abs(self.Xtr_rows - test_row), axis=1)
            best_k_distances_idx = np.argpartition(distances,k)[0:k]
            best_k_categories = self.Ytr[best_k_distances_idx]
            print("Best categories", best_k_categories)
            res[test_index] = stats.mode(best_k_categories)[0][0]
        return res
