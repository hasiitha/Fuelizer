using Fuelizer.Models.Customer;
using Fuelizer.Models.Suppliers;
using Fuelizer.Services.Customers;
using Fuelizer.Services.Suppliers;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace Fuelizer.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CustomerController : ControllerBase
    {
        private readonly ICustomerService customerService;

        //controller constructor
        public CustomerController(ICustomerService customerService)
        {
            this.customerService = customerService;

        }
        // GET: api/<CustomerController>
        [HttpGet]
        public ActionResult<List<Customer>> Get()
        {
            return customerService.Get();
        }

        // GET api/<CustomerController>/5
        [HttpGet("{id}")]
        public ActionResult<Customer> Get(string id)
        {
            var customer = customerService.Get(id);
            if (customer == null)
            {
                return NotFound($"customer with id = {id} not found");
            }
            return customer;
        }

        // POST api/<CustomerController>
        [HttpPost]
        public ActionResult<Customer> Post([FromBody] Customer customer)
        {
            customerService.Create(customer);
            return CreatedAtAction(nameof(Get), new { id = customer.Id }, customer);
        }

        // PUT api/<CustomerController>/5
        [HttpPut("{id}")]
        public ActionResult Put(string id, [FromBody] Customer customer)
        {
            var existingcustomer = customerService.Get(id);
            if (existingcustomer == null)
            {
                return NotFound($"Customer with id = {id} not found");
            }
            customerService.Update(id, customer);
            return NoContent();


        }

        // DELETE api/<CustomerController>/5
        [HttpDelete("{id}")]
        public ActionResult Delete(String id)
        {


            var customer = customerService.Get(id);
            if (customer == null)
            {
                return NotFound($"Customer with id = {id} not found");
            }
            customerService.Remove(customer.Id);
            return Ok($"Customer with id = {id} deleted");
        }

        // GET api/<CustomerController>/ByName
        [HttpGet("ByName/{name}")]
        public ActionResult<Customer> GetByName(string name)
        {
            var customer = customerService.GetByName(name);
            if (customer == null)
            {
                return NotFound($"customer with id = {name} not found");
            }
            return customer;
        }
    }
}
