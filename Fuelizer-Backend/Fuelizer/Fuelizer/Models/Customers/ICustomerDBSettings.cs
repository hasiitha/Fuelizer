namespace Fuelizer.Models.Customer
{
    public interface ICustomerDBSettings
    {
        string CustomerCollectionName { get; set; }
        string ConnectionString { get; set; }
        string DatabaseName { get; set; }
    }
}
