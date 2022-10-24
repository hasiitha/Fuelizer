using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace Fuelizer.Models.Suppliers
{
    [BsonIgnoreExtraElements]

    public class Supplier
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { set; get; } = String.Empty;

        [BsonElement("username")]
        public string UserName { set; get; } = String.Empty;

        [BsonElement("nic")]
        public string Nic { set; get; } = String.Empty;

        [BsonElement("mobileNumber")]
        public string MobileNumber { set; get; } = String.Empty;

        [BsonElement("email")]
        public string Email { set; get; } = String.Empty;

    }
}
