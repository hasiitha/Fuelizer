using Fuelizer.Models.FuelStations;
using Fuelizer.Services.FuelStations;
using Microsoft.AspNetCore.Mvc;

namespace Fuelizer.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class FuelStationController : ControllerBase
    {

        private readonly IFuelStationService fuelstationservice;

        //controller constructor
        public FuelStationController(IFuelStationService fuelStationService)
        {
            this.fuelstationservice = fuelStationService;
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
                return NotFound($"supplier with id = {id} not found");
            }
            return supplier;
        }

        // POST api/<FuelStationController>
        [HttpPost]
        public ActionResult<FuelStation> Post([FromBody] FuelStation supplier)
        {
            fuelstationservice.Create(supplier);
            return CreatedAtAction(nameof(Get), new { id = supplier.Id }, supplier);
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


    }
}
