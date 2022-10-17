namespace Fuelizer.Models.FuelStations
{
    public class FuelStationDBSettings : IFuelStationDBSettings
    {
        public string FuelStationCollectionName { get; set; } = String.Empty;
        public string ConnectionString { get; set; } = String.Empty;
        public string DatabaseName { get; set; } = String.Empty;
    }
}
