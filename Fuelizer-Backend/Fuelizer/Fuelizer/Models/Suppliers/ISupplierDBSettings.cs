namespace Fuelizer.Models.Suppliers
{
    public interface ISupplierDBSettings
    {
        string SupplierCollectionName { get; set; }
        string ConnectionString { get; set; }
        string DatabaseName { get; set; }

    }
}
