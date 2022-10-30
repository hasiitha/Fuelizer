using MongoDB.Bson.Serialization.Attributes;
using MongoDB.Bson;

namespace Fuelizer.Models.FuelQueue
{
    public class FuelQueue
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { set; get; } = String.Empty;

        [BsonElement("departureTime")]
        public string DepartureTime { set; get; } = String.Empty;

        [BsonElement("arrivalTime")]
        public string ArrivalTime { set; get; } = String.Empty;

        [BsonElement("fuelType")]
        public string FuelType { set; get; } = String.Empty;

        [BsonElement("vehicleType")]
        public string VehicleType { set; get; } = String.Empty;

    }
}
