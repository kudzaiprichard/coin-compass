from http import HTTPStatus
from flask import Blueprint, jsonify, request, current_app
from src.model.candlestick import Candlestick
from src.service.predict_service import predict
from marshmallow import ValidationError

predict_bp = Blueprint("predict_controller", __name__, url_prefix="/api/v1/predict")

@predict_bp.post("/")
def make_prediction():
    print("hello..............")
    schema = Candlestick()

    try:
        candlestick_data = request.get_json()
        print("candle............", candlestick_data)
        candlestick = schema.load(candlestick_data)
        print("second phase:" ,candlestick)
        prediction = predict(candlestick)

        # Return prediction in JSON format with explicit content type
        response = jsonify({
            "Prediction": prediction.tolist()[0]  # Convert NumPy array to list if needed
        })
        response.headers["Content-Type"] = "application/json"  # Explicitly set content type

        return response, HTTPStatus.OK

    except ValidationError as err:
        # Return validation error message in JSON format
        return jsonify({
            "Error": err.messages
        }), HTTPStatus.BAD_REQUEST

    except FileNotFoundError as e:
        # Log and handle model file not found error
        current_app.logger.error(f"FileNotFoundError: {str(e)}")
        return jsonify({
            "Error": "Model file not found"
        }), HTTPStatus.INTERNAL_SERVER_ERROR

    except Exception as e:
        # Log and handle other exceptions
        current_app.logger.error(f"Exception: {str(e)}")
        return jsonify({
            "Error": "Internal Server Error"
        }), HTTPStatus.INTERNAL_SERVER_ERROR
