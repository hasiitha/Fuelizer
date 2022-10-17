using Fuelizer.Models.Customer;

namespace Fuelizer.Services.Customers
{
    public interface ICustomerService
    {
        List<Customer> Get();

        Customer Get(string id);

        Customer Create(Customer customer);

        void Update(string id, Customer customer);

        void Remove(string id);
    }
}
