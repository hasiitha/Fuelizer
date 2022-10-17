using MongoDB.Bson.Serialization.Attributes;
using MongoDB.Bson;

namespace Fuelizer.Models.Customer
{
    [BsonIgnoreExtraElements]
    public class Customer
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { set; get; } = String.Empty;

        [BsonElement("userName")]
        public string UserName { set; get; } = String.Empty;

        [BsonElement("nic")]
        public string Nic { set; get; } = String.Empty;

        [BsonElement("vehicleType")]
        public string VehicleType { set; get; } = String.Empty;

        [BsonElement("fuelType")]
        public string FuelType { set; get; } = String.Empty;

        [BsonElement("vehicleNumber")]
        public string VehicleNumber { set; get; } = String.Empty;

        [BsonElement("chasisNumber")]
        public string ChasisNumber { set; get; } = String.Empty;

        [BsonElement("mobileNumber")]
        public string MobileNumber { set; get; } = String.Empty;
    }
}
