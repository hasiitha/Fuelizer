using Fuelizer.Models.Customer;
using Fuelizer.Models.Suppliers;
using MongoDB.Driver;

namespace Fuelizer.Services.Customers
{
    public class CustomerService : ICustomerService
    {
        private readonly IMongoCollection<Customer> _customer;

        public CustomerService(ICustomerDBSettings settings, IMongoClient mongoClient)
        {
            var database = mongoClient.GetDatabase(settings.DatabaseName);
            _customer = database.GetCollection<Customer>(settings.CustomerCollectionName);

        }
        public Customer Create(Customer customer)
        {
            _customer.InsertOne(customer);
            return customer;
        }

        public List<Customer> Get()
        {
            return _customer.Find(customer => true).ToList();
        }

        public Customer Get(string id)
        {
            return _customer.Find(customer => customer.Id == id).FirstOrDefault();
        }

        public void Remove(string id)
        {
            _customer.DeleteOne(customer => customer.Id == id);
        }

        public void Update(string id, Customer customer)
        {
            _customer.ReplaceOne(cus => cus.Id == id, customer);
        }

        public Customer GetByName(string name)
        {
            return _customer.Find(customer => customer.UserName == name).FirstOrDefault();
        }
    }
}
