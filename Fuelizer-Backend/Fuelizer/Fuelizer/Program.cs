using Fuelizer.Models.Suppliers;
using Fuelizer.Services.Suppliers;
using Microsoft.Extensions.Options;
using MongoDB.Driver;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.Configure<SupplierDBSettings>(
    builder.Configuration.GetSection(nameof(SupplierDBSettings)));

builder.Services.AddSingleton<ISupplierDBSettings>(sp => sp.GetRequiredService<IOptions<SupplierDBSettings>>().Value);

builder.Services.AddSingleton<IMongoClient>(s => new MongoClient(builder.Configuration.GetValue<string>("Connection:ConnectionString")));


builder.Services.AddScoped<ISupplierService, SupplierService>();


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
