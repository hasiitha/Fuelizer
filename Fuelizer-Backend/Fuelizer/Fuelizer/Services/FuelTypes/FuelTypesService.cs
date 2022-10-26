using Fuelizer.Models.FuelTypes;
using Fuelizer.Models.Suppliers;
using MongoDB.Driver;

namespace Fuelizer.Services.FuelTypes
{
    public class FuelTypesService : IFuelTypesService
    {
        private readonly IMongoCollection<FuelType> _fueltype;

        public FuelTypesService(IFuelTypesDBSettings settings, IMongoClient mongoClient)
        {
            var database = mongoClient.GetDatabase(settings.DatabaseName);
            _fueltype = database.GetCollection<FuelType>(settings.FuelTypesCollectionName);

        }


        public FuelType Create(FuelType fuelType)
        {
            _fueltype.InsertOne(fuelType);
            return fuelType;
        }

        public List<FuelType> Get()
        {
            return _fueltype.Find(fueltype => true).ToList();
        }

        public FuelType Get(string id)
        {
            return _fueltype.Find(fuelType => fuelType.Id == id).FirstOrDefault();
        }

        public void Remove(string id)
        {
            _fueltype.DeleteOne(fuelType => fuelType.Id == id);
        }

        public void Update(string id, FuelType fuelType)
        {
            _fueltype.ReplaceOne(sup => sup.Id == id, fuelType);
        }

        public List<FuelType> GetFuelTypesofStation(string stationId)
        {
            return _fueltype.Find(fuelType => fuelType.StationId == stationId).ToList();
        }

        void IFuelTypesService.UpdateFuelType(string id, FuelType fuelType)
        {
            _fueltype.ReplaceOne(sup => sup.Id == id, fuelType);
        }
    }
}
