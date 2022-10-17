using Fuelizer.Models.FuelStations;

namespace Fuelizer.Services.FuelStations;

    public interface IFuelStationService
    {

        List<FuelStation> Get();

        FuelStation Get(string id);

        FuelStation Create(FuelStation fuelStation);

        void Update(string id, FuelStation fuelStation);

        void Remove(string id);
    }



