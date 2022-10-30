using Fuelizer.Models.Suppliers;
using Fuelizer.Services.Suppliers;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace Fuelizer.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class SupplierController : ControllerBase
    {
        
            private readonly ISupplierService supplierservice;

            //controller constructor
            public SupplierController(ISupplierService supplierService)
            {
                this.supplierservice = supplierService;

            }



            // GET: api/<SupplierController>
            [HttpGet]
        public ActionResult<List<Supplier>> Get()
        {
            return supplierservice.Get();
        }


        // GET api/<SupplierController>/5
        [HttpGet("{id}")]
        public ActionResult<Supplier> Get(string id)
        {


            var supplier = supplierservice.Get(id);
            if (supplier == null)
            {
                return NotFound($"supplier with id = {id} not found");
            }
            return supplier;
        }

        // POST api/<SupplierController>
        [HttpPost]
        public ActionResult<Supplier> Post([FromBody] Supplier supplier)
        {
            supplierservice.Create(supplier);
            return CreatedAtAction(nameof(Get), new { id = supplier.Id }, supplier);
        }

        // PUT api/<SupplierController>/5
        [HttpPut("{id}")]
        public ActionResult Put(string id, [FromBody] Supplier supplier)
        {
            var existingsupplier = supplierservice.Get(id);
            if (existingsupplier == null)
            {
                return NotFound($"Supplier with id = {id} not found");
            }
            supplierservice.Update(id, supplier);
            return NoContent();


        }

        // DELETE api/<SupplierController>/5
        [HttpDelete("{id}")]
        public ActionResult Delete(String id)
        {


            var supplier = supplierservice.Get(id);
            if (supplier == null)
            {
                return NotFound($"supplier with id = {id} not found");
            }
            supplierservice.Remove(supplier.Id);
            return Ok($"supplier with id = {id} deleted");
        }


        // GET api/<SupplierController>/5
        [HttpGet("getUserIdByUserName")]
        public ActionResult<String> GetIdByUserName(string username)
        {


            var supplier = supplierservice.GetIdByUserName(username);
            if (supplier == null)
            {
                return NotFound($"supplier with userName = {username} not found");
            }
            return supplier.Id;
        }



    }
}
