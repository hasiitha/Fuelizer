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



    }
}
