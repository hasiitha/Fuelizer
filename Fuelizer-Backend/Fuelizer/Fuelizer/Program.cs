using Fuelizer.Models.FuelTypes;
using Fuelizer.Models.Customer;
using Fuelizer.Models.Suppliers;
using Fuelizer.Services.FuelTypes;
using Fuelizer.Services.Customers;
using Fuelizer.Services.Suppliers;
using Microsoft.Extensions.Options;
using MongoDB.Driver;
using Fuelizer.Models.FuelStations;
using Fuelizer.Services.FuelStations;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.Configure<SupplierDBSettings>(
    builder.Configuration.GetSection(nameof(SupplierDBSettings)));

builder.Services.Configure<FuelTypesDBSettings>(
    builder.Configuration.GetSection(nameof(FuelTypesDBSettings)));

builder.Services.Configure<CustomerDBSettings>(
    builder.Configuration.GetSection(nameof(CustomerDBSettings)));

builder.Services.Configure<FuelStationDBSettings>(
    builder.Configuration.GetSection(nameof(FuelStationDBSettings)));




builder.Services.AddSingleton<ISupplierDBSettings>(sp => sp.GetRequiredService<IOptions<SupplierDBSettings>>().Value);

builder.Services.AddSingleton<IFuelTypesDBSettings>(sp => sp.GetRequiredService<IOptions<FuelTypesDBSettings>>().Value);

builder.Services.AddSingleton<ICustomerDBSettings>(cp => cp.GetRequiredService<IOptions<CustomerDBSettings>>().Value);

builder.Services.AddSingleton<IFuelStationDBSettings>(cp => cp.GetRequiredService<IOptions<FuelStationDBSettings>>().Value);

//same connection string for all
builder.Services.AddSingleton<IMongoClient>(s => new MongoClient(builder.Configuration.GetValue<string>("Connection:ConnectionString")));



builder.Services.AddScoped<IFuelTypesService, FuelTypesService>();
builder.Services.AddScoped<ISupplierService, SupplierService>();

builder.Services.AddScoped<ICustomerService, CustomerService>();
builder.Services.AddScoped<IFuelStationService, FuelStationService>();


builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
