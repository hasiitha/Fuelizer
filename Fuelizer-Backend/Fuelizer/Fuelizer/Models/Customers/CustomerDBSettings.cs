namespace Fuelizer.Models.Customer
{
    public class CustomerDBSettings : ICustomerDBSettings
    {
        public string CustomerCollectionName { get; set; } = String.Empty;
        public string ConnectionString { get; set; } = String.Empty;
        public string DatabaseName { get; set; } = String.Empty;
    }
}
