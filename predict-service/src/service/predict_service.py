from flask import current_app, jsonify
import pickle

"""
You can create multiple predictions for different 
Cryptocurrency by traing the same model with different
cryptocurrency data given in the model machine learning folder
"""
def predict(candlestick):
    #Load machine learnig model
    model = pickle.load(open("btc_model.pkl","rb"))

    # outputs for the classification
    features = [[candlestick.high, candlestick.low, candlestick.close]]

    #Return prediction
    return model.predict(features)
    