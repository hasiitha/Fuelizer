using Fuelizer.Models.FuelTypes;
using Fuelizer.Models.Suppliers;

namespace Fuelizer.Services.FuelTypes
{
    public interface IFuelTypesService
    {
        List<FuelType> Get();

        FuelType Get(string id);

        FuelType Create(FuelType fuelType);

        void Update(string id, FuelType fuelType);

        void Remove(string id);

        List<FuelType> GetFuelTypesofStation(string stationId);

        void UpdateFuelType(string id, FuelType fuelType);

    }
}
