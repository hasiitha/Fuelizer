using Fuelizer.Models.Suppliers;

namespace Fuelizer.Services.Suppliers
{
    public interface ISupplierService
    {

        List<Supplier> Get();

        Supplier Get(string id);

        Supplier Create(Supplier supplier);

        void Update(string id, Supplier supplier);

        void Remove(string id);
    }
}
