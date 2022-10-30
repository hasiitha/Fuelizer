using Fuelizer.Models.Suppliers;
using MongoDB.Driver;

namespace Fuelizer.Services.Suppliers
{
    public class SupplierService : ISupplierService
    {
        private readonly IMongoCollection<Supplier> _supplier;

        public SupplierService(ISupplierDBSettings settings, IMongoClient mongoClient)
        {
            var database = mongoClient.GetDatabase(settings.DatabaseName);
            _supplier = database.GetCollection<Supplier>(settings.SupplierCollectionName);

        }


        public Supplier Create(Supplier supplier)
        {
            _supplier.InsertOne(supplier);
            return supplier;
        }

        public List<Supplier> Get()
        {
            return _supplier.Find(supplier => true).ToList();
        }

        public Supplier Get(string id)
        {
            return _supplier.Find(supplier => supplier.Id == id).FirstOrDefault();
        }

        public void Remove(string id)
        {

            _supplier.DeleteOne(supplier => supplier.Id == id);

        }

        public void Update(string id, Supplier supplier)
        {

            _supplier.ReplaceOne(sup => sup.Id == id, supplier);

        }

        Supplier ISupplierService.GetIdByUserName(string userName)
        {
            return _supplier.Find(supplier => supplier.UserName == userName).FirstOrDefault();
        }
    }
}
