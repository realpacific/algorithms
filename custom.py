from sklearn.naive_bayes import MultinomialNB


# noinspection SpellCheckingInspection
class CustomClassifier(MultinomialNB):

    def __init__(self, alpha=1.0, fit_prior=True, class_prior=None, tdidf=None, count_vectorizer=None):
        self.tfidf_transformer = tdidf
        self.count_vectorizer = count_vectorizer
        super().__init__(alpha, fit_prior, class_prior)

    def predict(self, X):
        if self.tfidf_transformer is not None and self.count_vectorizer is not None:
            X = self.tfidf_transformer.transform(self.count_vectorizer.transform(X))
        return super().predict(X)
