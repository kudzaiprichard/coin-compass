from marshmallow import Schema, fields

class Candlestick(Schema):
    symbol = fields.Str(dump_only=True)
    volume = fields.Str(dump_only=True)
    closeTime = fields.Float(dump_only=True)
    quoteAssetVolume = fields.Str(dump_only=True)
    numberOfTrades = fields.Float(dump_only=True)
    takerBuyBaseAssetVolume = fields.Str(dump_only=True)
    takerBuyBaseAssetVolume = fields.Str(dump_only=True)
    openTime = fields.Str(dump_only=True)
    open = fields.Str(dump_only=True)
    high = fields.Str(dump_only=True)
    low = fields.Str(dump_only=True)
    close = fields.Str(dump_only=True)


