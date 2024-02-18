from flask import Flask
from src.controller.predict_controller import predict_controller


def create_app(test_config=None):
    rest_port = 8050
    # Todo: unistall flask eureka and install py-eureka-client
    # $ pip install py-eureka-client
    
    eureka_client.init(eureka_server="http://localhost:8761/eureka",
                    app_name="predict-service",
                    instance_port=rest_port)
    
    
    app = Flask(__name__)
    
    #Register the controller
    app.register_blueprint(predict_controller)

    return app