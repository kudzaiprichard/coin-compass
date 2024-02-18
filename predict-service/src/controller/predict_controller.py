from http import HTTPStatus
from flask import Blueprint, jsonify, request
from src.model.candlestick import Candlestick
from src.service.predict_service import predict
from marshmallow import ValidationError

predict_controller = Blueprint("predict_controller",__name__,url_prefix="/api/v1/predict")

@predict_controller.post("/")
def predict():
    #Create new candle stick shcema
    schema = Candlestick()
    
    try:
        #Parse candle stick data
        candlestick = schema.load(request.get_json())
        
        #Predict crypto currency
        prediction = predict(candlestick)
        
        #Return predicted in jason format
        return jsonify({
                        "Prediction": prediction
                    }),HTTPStatus.OK
    
    #Raise exception and return error message in jason format
    except ValidationError as err:
        return jsonify({
                        "Error": err.messages
                    }),HTTPStatus.BAD_REQUEST
