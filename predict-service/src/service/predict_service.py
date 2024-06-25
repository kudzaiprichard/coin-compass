# src/service/predict_service.py

import pickle
from flask import current_app
import numpy as np


def predict(candlestick):
    try:
        # Extract relevant features from candlestick data
        open_price = float(candlestick.get('open', 0.0))
        high_price = float(candlestick.get('high', 0.0))
        low_price = float(candlestick.get('low', 0.0))
        volume = float(candlestick.get('volume', 0.0))
        close_price = float(candlestick.get('close', 0.0))

        # Load machine learning model
        model_path = "src/service/btc_model.pkl"
        with open(model_path, "rb") as f:
            model = pickle.load(f)

        # Prepare features for prediction
        features = np.array([[open_price, high_price, low_price, volume, close_price]])

        # Return prediction
        prediction = model.predict(features)

        # Convert prediction to list if it's not already a list
        # if isinstance(prediction, np.ndarray):
        #     prediction = prediction.tolist()
        # elif not isinstance(prediction, list):
        #     prediction = [prediction]  # Handle scalar predictions

        return prediction

    except KeyError as e:
        raise ValueError(f"Missing required key in candlestick data: {str(e)}")

    except FileNotFoundError as e:
        raise FileNotFoundError(f"Model file '{model_path}' not found.")

    except Exception as e:
        raise Exception(f"Error during prediction: {str(e)}")
