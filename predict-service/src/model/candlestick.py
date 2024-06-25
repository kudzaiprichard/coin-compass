# src/model/candlestick.py

from marshmallow import Schema, fields

class Candlestick(Schema):
    symbol = fields.Str(required=True)
    volume = fields.Float(required=True)       # Adjusted to match numeric type
    open_price = fields.Float(required=True)   # Adjusted to match numeric type
    high_price = fields.Float(required=True)   # Adjusted to match numeric type
    low_price = fields.Float(required=True)    # Adjusted to match numeric type
    close_price = fields.Float(required=True)  # Adjusted to match numeric type

