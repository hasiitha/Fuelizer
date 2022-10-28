using Fuelizer.Models.FuelStations;
using Fuelizer.Services.FuelStations;
using MongoDB.Driver;

namespace Fuelizer.Services.Suppliers
{
    public class FuelStationService : IFuelStationService
    {
        private readonly IMongoCollection<FuelStation> _fuelStation;

        public FuelStationService(IFuelStationDBSettings settings, IMongoClient mongoClient)
        {
            var database = mongoClient.GetDatabase(settings.DatabaseName);
            _fuelStation = database.GetCollection<FuelStation>(settings.FuelStationCollectionName);

        }

        public List<FuelStation> GetMyFuelStation(string OwnerId)
        {
            return _fuelStation.Find(fuelStation => fuelStation.OwnerId == OwnerId).ToList();
        }

        FuelStation IFuelStationService.Create(FuelStation fuelStation)
        {
            _fuelStation.InsertOne(fuelStation);
            return fuelStation;
        }

        List<FuelStation> IFuelStationService.Get()
        {
            return _fuelStation.Find(fuelStation => true).ToList();
        }

        FuelStation IFuelStationService.Get(string id)
        {
            return _fuelStation.Find(fuelStation => fuelStation.Id == id).FirstOrDefault();
        }

        void IFuelStationService.Remove(string id)
        {
            _fuelStation.DeleteOne(fuelStation => fuelStation.Id == id);
        }

        void IFuelStationService.Update(string id, FuelStation fuelStation)
        {
            _fuelStation.ReplaceOne(sup => sup.Id == id, fuelStation);
        }

    }
}
