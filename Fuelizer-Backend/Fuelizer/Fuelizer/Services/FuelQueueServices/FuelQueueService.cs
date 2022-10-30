using Fuelizer.Models.FuelQueue;
using Fuelizer.Models.FuelQueues;
using Fuelizer.Models.Suppliers;
using MongoDB.Driver;

namespace Fuelizer.Services.FuelQueueServices
{
    public class FuelQueueService : IFuelQueueService
    {
        private readonly IMongoCollection<FuelQueue> _fuelQueue;

        public FuelQueueService(IFuelQueue settings, IMongoClient mongoClient)
        {
            var database = mongoClient.GetDatabase(settings.DatabaseName);
            _fuelQueue = database.GetCollection<FuelQueue>(settings.FuelQueueCollectionName);

        }

        public FuelQueue Create(FuelQueue fuelQueue)
        {
            _fuelQueue.InsertOne(fuelQueue);
            return fuelQueue;
        }

        public List<FuelQueue> Get()
        {
            return _fuelQueue.Find(fuelQeuqe => true).ToList();
        }

        public FuelQueue Get(string id)
        {
            return _fuelQueue.Find(f => f.Id == id).FirstOrDefault();
        }

        public void Remove(string id)
        {
            _fuelQueue.DeleteOne(f => f.Id == id);
        }

        public void Update(string id, FuelQueue supplier)
        {
            _fuelQueue.ReplaceOne(f => f.Id == id, supplier);
        }
    }
}
