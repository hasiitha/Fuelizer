namespace Fuelizer.Models.FuelTypes
{
    public class FuelTypesDBSettings : IFuelTypesDBSettings
    {
        public string FuelTypesCollectionName { get; set; } =String.Empty; 
        public string ConnectionString { get; set; } = String.Empty;
        public string DatabaseName { get; set; } = String.Empty;
    }
}
