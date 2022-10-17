namespace Fuelizer.Models.Suppliers
{
    public class SupplierDBSettings : ISupplierDBSettings
    {
        public string SupplierCollectionName { get; set; } = String.Empty; 
        public string ConnectionString { get; set; } = String.Empty ;
        public string DatabaseName { get ; set; } = String.Empty ;
    }
}
