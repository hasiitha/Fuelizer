using Fuelizer.Models.FuelStations;
using Fuelizer.Models.FuelTypes;
using Fuelizer.Models.Suppliers;
using Fuelizer.Services.FuelStations;
using Fuelizer.Services.FuelTypes;
using Fuelizer.Services.Suppliers;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace Fuelizer.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class FuelTypeController : ControllerBase
    {
        private readonly IFuelTypesService fueltypesservice;

        public FuelTypeController(IFuelTypesService fueltypesservice )
        {
            this.fueltypesservice = fueltypesservice;

        }


        // GET: api/<FuelTypeController>
        [HttpGet]
        public ActionResult<List<FuelType>> Get()
        {
            return fueltypesservice.Get();
        }

        // GET api/<FuelTypeController>/5
        [HttpGet("{id}")]
        public ActionResult<FuelType> Get(string id)
        {
            var fueltype = fueltypesservice.Get(id);
            if (fueltype == null)
            {
                return NotFound($"fueltype with id = {id} not found");
            }
            return fueltype;
        }

        // POST api/<FuelTypeController>
        [HttpPost]
        public ActionResult<Supplier> Post([FromBody] FuelType fueltype)
        {
            fueltypesservice.Create(fueltype);
            return CreatedAtAction(nameof(Get), new { id = fueltype.Id }, fueltype);
        }

        // PUT api/<FuelTypeController>/5
        [HttpPut("{id}")]
        public ActionResult Put(string id, [FromBody] FuelType fuelType)
        {
            var existingfueltype = fueltypesservice.Get(id);
            if (existingfueltype == null)
            {
                return NotFound($"fueltype with id = {id} not found");
            }
            fueltypesservice.Update(id, fuelType);
            return NoContent();

        }

        // DELETE api/<FuelTypeController>/5
        [HttpDelete("{id}")]
        public ActionResult Delete(string id)
        {
            var fueltype = fueltypesservice.Get(id);
            if (fueltype == null)
            {
                return NotFound($"fueltype with id = {id} not found");
            }
            fueltypesservice.Remove(fueltype.Id);
            return Ok($"supplier with id = {id} deleted");
        }


        // GET api/<FuelStationController>/5
        [HttpGet("getFuelTypesofStation/{stationId}")]
        public ActionResult<List<FuelType>> GetFuelTypesofStation(string stationId)
        {


            return fueltypesservice.GetFuelTypesofStation(stationId);
        }


        // PUT api/<FuelTypeController>/5
        [HttpPut("toUpdateArrivals/{id}")]
        public ActionResult PutToArrivals(string id, string arrivaltime)
        {
            var existingfueltype = fueltypesservice.Get(id);
            if (existingfueltype == null)
            {
                return NotFound($"fueltype with id = {id} not found");
            }
            existingfueltype.ArrivalTime = arrivaltime;
            var toUpdate = existingfueltype;
            fueltypesservice.Update(id, toUpdate);
            return NoContent();

        }


        // PUT api/<FuelTypeController>/5
        [HttpPut("toaddamount/{id}")]
        public ActionResult AddStorage(string id, string amount)
        {
            var existingfueltype = fueltypesservice.Get(id);
            if (existingfueltype == null)
            {
                return NotFound($"fueltype with id = {id} not found");
            }
            String  remain_amounts = existingfueltype.Remainder;
            int remain =  Int32.Parse(remain_amounts);
            int add = Int32.Parse(amount);
            int sum = add + remain;
            String sumval = sum.ToString();
            existingfueltype.Remainder = sumval;
            existingfueltype.Finish = false;
            var toUpdate = existingfueltype;
            fueltypesservice.Update(id, toUpdate);
            return NoContent();

        }


        // PUT api/<FuelTypeController>/5
        [HttpPut("finishFuel/{id}")]
        public ActionResult FinishFuel(string id)
        {
            var existingfueltype = fueltypesservice.Get(id);
            if (existingfueltype == null)
            {
                return NotFound($"fueltype with id = {id} not found");
            }
            existingfueltype.Finish = true;
            existingfueltype.Remainder = "0";
            var toUpdate = existingfueltype;
            fueltypesservice.Update(id, toUpdate);
            return NoContent();

        }

    }
}
