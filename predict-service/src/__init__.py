# src/__init__.py

from flask import Flask
from src.controller.predict_controller import predict_bp
import py_eureka_client.eureka_client as eureka_client


def create_app(test_config=None):
    rest_port = 8050
    eureka_client.init(eureka_server="http://localhost:8761/eureka/",
                       app_name="predict-service",
                       instance_port=rest_port)

    app = Flask(__name__)
    app.config['JSONIFY_TIMEOUT'] = 10  # Adjust timeout as needed

    # Register the controller blueprint with URL prefix
    app.register_blueprint(predict_bp, url_prefix='/api/v1/predict')

    return app
