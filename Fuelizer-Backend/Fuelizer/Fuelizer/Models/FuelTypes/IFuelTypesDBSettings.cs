namespace Fuelizer.Models.FuelTypes
{
    public interface IFuelTypesDBSettings
    {
        string FuelTypesCollectionName { get; set; }
        string ConnectionString { get; set; }
        string DatabaseName { get; set; }
    }
}
