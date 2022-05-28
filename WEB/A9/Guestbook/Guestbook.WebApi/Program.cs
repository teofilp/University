using Guestbook.Business.Users.Handlers;
using Guestbook.Data;
using Guestbook.WebApi.Middleware;
using MediatR;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
builder.Services.AddMediatR(typeof(GetUsersQueryHandler).Assembly);
builder.Services.AddDbContext<GuestbookContext>(
    options => options.UseSqlServer(builder.Configuration.GetConnectionString("DefaultConnection")));
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.AddCors(options => options.AddDefaultPolicy(policy => 
    policy.AllowAnyOrigin()
    .AllowAnyMethod()
    .AllowAnyHeader()));

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

// app.UseAuthorization();
app.UseCustomAuthorization();

app.UseCors();

app.MapControllers();

app.Run();
