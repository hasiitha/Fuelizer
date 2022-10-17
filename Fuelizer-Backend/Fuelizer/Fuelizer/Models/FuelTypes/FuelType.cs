using MongoDB.Bson.Serialization.Attributes;
using MongoDB.Bson;

namespace Fuelizer.Models.FuelTypes
{
    [BsonIgnoreExtraElements]
    public class FuelType
    {  
            [BsonId]
            [BsonRepresentation(BsonType.ObjectId)]
            public string Id { set; get; } = String.Empty;

            [BsonElement("stationId")]
            [BsonRepresentation(BsonType.ObjectId)]
            public string StationId { set; get; } = String.Empty;

            [BsonElement("Type")]
            public string Type { set; get; } = String.Empty;

            [BsonElement("reaminder")]
            public string Remainder { set; get; } = String.Empty;

            [BsonElement("capacity")]
            public string Capacity { set; get; } = String.Empty;

            [BsonElement("noOfCars")]
            public string NoOfCars { set; get; } = String.Empty;

            [BsonElement("noOfVans")]
            public string NoOfVans { set; get; } = String.Empty;

            [BsonElement("noOfMotocycles")]
            public string NoOfMotocycles { set; get; } = String.Empty;

            [BsonElement("noOfLorries")]
            public string NoOfLorries { set; get; } = String.Empty;

            [BsonElement("noOfTrishaw")]
            public string NoOfTrishaw { set; get; } = String.Empty;

            [BsonElement("arrival")]
            public string ArrivalTime { set; get; } = String.Empty;


            [BsonElement("finish")]
            public bool Finish { set; get; } 

        
    }
}
