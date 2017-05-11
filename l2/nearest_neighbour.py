import numpy as np

class NearestNeighbour():
    def train(self, Xtr_rows, Ytr):
        self.Xtr_rows = Xtr_rows
        self.Ytr = Ytr

    def predict(self, Xte_rows):
        #the train data is an array of size numTrainingExample x trainingArraySize. in our case: 50,000 x 3072
        #each row of Xte_rows is an array of size 3072 representing an image
        #compute the sums of abs difference between test row and each row of train array as an 50,000 elements array
        num_tests = Xte_rows.shape[0]
        res = np.zeros(num_tests, dtype = self.Ytr.dtype) # the result is an array with the best classification for each example
        for test_index in range(num_tests):
            test_row = Xte_rows[test_index,:]
            distances = np.sum(abs(self.Xtr_rows - test_row), axis=1)
            best_index = np.argmin(distances)
            best_classification = self.Ytr[best_index]
            res[test_index] = best_classification
            print('Classified test image ', test_index, 'as category ', best_classification)
        return res
