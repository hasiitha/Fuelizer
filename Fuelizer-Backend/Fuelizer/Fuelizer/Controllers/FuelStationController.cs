using Fuelizer.Models.FuelStations;
using Fuelizer.Models.FuelTypes;
using Fuelizer.Services.FuelStations;
using Fuelizer.Services.FuelTypes;
using Microsoft.AspNetCore.Mvc;

namespace Fuelizer.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class FuelStationController : ControllerBase
    {

        private readonly IFuelStationService fuelstationservice;
        private readonly IFuelTypesService _fueltypeservice;

        //controller constructor
        public FuelStationController(IFuelStationService fuelStationService,IFuelTypesService fueltypessServices)
        {
            this.fuelstationservice = fuelStationService;
            this._fueltypeservice = fueltypessServices;
        }


        // GET: api/<FuelStationController>
        [HttpGet]
        public ActionResult<List<FuelStation>> Get()
        {
            return fuelstationservice.Get();
        }


        // GET api/<FuelStationController>/5
        [HttpGet("{id}")]
        public ActionResult<FuelStation> Get(string id)
        {


            var supplier = fuelstationservice.Get(id);
            if (supplier == null)
            {
                return NotFound($"fuelstation with id = {id} not found");
            }
            return supplier;
        }

        // POST api/<FuelStationController>
        [HttpPost]
        public ActionResult<FuelStation> Post([FromBody] FuelStation fuelStation)
        {
            fuelstationservice.Create(fuelStation);

            FuelType petrol = new FuelType();
            petrol.StationId = fuelStation.Id;
            petrol.Type = "petrol";
            petrol.Capacity = "0";
            petrol.Remainder = "0";
            petrol.ArrivalTime = "Not Added";
            petrol.NoOfCars = "0";
            petrol.NoOfVans = "0";
            petrol.NoOfLorries = "0";
            petrol.Finish = true;
            petrol.NoOfTrishaw = "0";
            petrol.NoOfMotocycles = "0";
            _fueltypeservice.addFuelTypes(petrol);

            FuelType petrol95 = new FuelType();
            petrol95.StationId = fuelStation.Id;
            petrol95.Type = "petrol95";
            petrol95.Capacity = "0";
            petrol95.Remainder = "0";
            petrol95.ArrivalTime = "Not Added";
            petrol95.NoOfCars = "0";
            petrol95.NoOfVans = "0";
            petrol95.NoOfLorries = "0";
            petrol95.Finish = true;
            petrol95.NoOfTrishaw = "0";
            petrol95.NoOfMotocycles = "0";
            _fueltypeservice.addFuelTypes(petrol95);

            FuelType diesel = new FuelType();
            diesel.StationId = fuelStation.Id;
            diesel.Type = "diesel";
            diesel.Capacity = "0";
            diesel.Remainder = "0";
            diesel.ArrivalTime = "Not Added";
            diesel.NoOfCars = "0";
            diesel.NoOfVans = "0";
            diesel.NoOfLorries = "0";
            diesel.Finish = true;
            diesel.NoOfTrishaw = "0";
            diesel.NoOfMotocycles = "0";
            _fueltypeservice.addFuelTypes(diesel);



            FuelType superdiesel = new FuelType();
            superdiesel.StationId = fuelStation.Id;
            superdiesel.Type = "superdiesel";
            superdiesel.Capacity = "0";
            superdiesel.Remainder = "0";
            superdiesel.ArrivalTime = "Not Added";
            superdiesel.NoOfCars = "0";
            superdiesel.NoOfVans = "0";
            superdiesel.NoOfLorries = "0";
            superdiesel.Finish = true;
            superdiesel.NoOfTrishaw = "0";
            superdiesel.NoOfMotocycles = "0";
            _fueltypeservice.addFuelTypes(superdiesel);




            return CreatedAtAction(nameof(Get), new { id = fuelStation.Id }, fuelStation);
        }

        // PUT api/<FuelStationController>/5
        [HttpPut("{id}")]
        public ActionResult Put(string id, [FromBody] FuelStation fuelStation)
        {
            var existingsupplier = fuelstationservice.Get(id);
            if (existingsupplier == null)
            {
                return NotFound($"Supplier with id = {id} not found");
            }
            fuelstationservice.Update(id, fuelStation);
            return NoContent();


        }

        // DELETE api/<FuelStationController>/5
        [HttpDelete("{id}")]
        public ActionResult Delete(String id)
        {


            var fuelStation = fuelstationservice.Get(id);
            if (fuelStation == null)
            {
                return NotFound($"fuel station with id = {id} not found");
            }
            fuelstationservice.Remove(fuelStation.Id);
            return Ok($"fuel station with id = {id} deleted");
        }


        // GET api/<FuelStationController>/5
        [HttpGet("getmyfuelstations/{ownerId}")]
        public  ActionResult<List<FuelStation>> GetMyFuelStation(string ownerId)
        {


            return fuelstationservice.GetMyFuelStation(ownerId);
        }


        [HttpPut("changeStatus/{id}")]
        public ActionResult changeStatus(string id)
        {
            var existingfuelstation = fuelstationservice.Get(id);
            if (existingfuelstation == null)
            {
                return NotFound($"fueltype with id = {id} not found");
            }
          

            FuelStation toUpdate = existingfuelstation;
            toUpdate.OpenCloseStatus = !toUpdate.OpenCloseStatus;
            fuelstationservice.Update(id, toUpdate);
            return NoContent();

        }

    }
}
