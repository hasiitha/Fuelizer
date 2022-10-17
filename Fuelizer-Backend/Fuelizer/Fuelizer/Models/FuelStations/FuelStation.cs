using MongoDB.Bson.Serialization.Attributes;
using MongoDB.Bson;
using static System.Collections.Specialized.BitVector32;
using System;

namespace Fuelizer.Models.FuelStations
{
    [BsonIgnoreExtraElements]
    public class FuelStation
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { set; get; } = String.Empty;

        [BsonElement("stationName")]
        public string StationName { set; get; } = String.Empty;
        [BsonElement("location")]
        public string Location { set; get; } = String.Empty;
        [BsonElement("status")]
        public bool OpenCloseStatus { set; get; }
    }
}

