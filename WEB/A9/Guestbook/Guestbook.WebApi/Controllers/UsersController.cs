using Guestbook.Business.Users.Commands;
using Guestbook.Business.Users.Queries;
using Guestbook.Data.Models;
using MediatR;
using Microsoft.AspNetCore.Mvc;

namespace Guestbook.WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UsersController : ControllerBase
    {
        private IMediator _mediator;

        public UsersController(IMediator mediator)
        {
            _mediator = mediator;
        }

        [HttpGet]
        public Task<List<User>> GetUsers()
        {
            return _mediator.Send(new GetUsersQuery());
        }

        [HttpPost]
        public Task<int> AddUser(AddUserCommand command)
        {
            return _mediator.Send(command);
        }

        [HttpPost("login")]
        public Task<int> Login(LoginCommand command)
        {
            return _mediator.Send(command);
        }
    }
}
