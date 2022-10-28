namespace Fuelizer.Models.FuelQueues
{
    public interface IFuelQueue
    {
        string FuelQueueCollectionName { get; set; }
        string ConnectionString { get; set; }
        string DatabaseName { get; set; }
    }
}
