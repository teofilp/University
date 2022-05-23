using Guestbook.Business.Guestbooks.Commands;
using Guestbook.Business.Guestbooks.Queries;
using Guestbook.Business.Models;
using MediatR;
using Microsoft.AspNetCore.Mvc;
using Models = Guestbook.Data.Models;

namespace Guestbook.WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class GuestbooksController : ControllerBase
    {
        private IMediator _mediator;

        public GuestbooksController(IMediator mediator)
        {
            _mediator = mediator;
        }

        [HttpGet]
        public Task<GetGuestbooksResponse> GetGuestbooks([FromQuery] int currentPage, [FromQuery] string? search)
        {
            return _mediator.Send(new GetGuestbooksQuery
            {
                SearchKey = search ?? "",
                CurrentPage = currentPage
            });
        }

        [HttpGet("{id}")]
        public Task<Models.Guestbook> GetById(int id)
        {
            return _mediator.Send(new GetByIdQuery
            {
                GuestbookId = id
            });
        }

        [HttpPost]
        public Task<int> AddGuestbook(AddGuestbookCommand command)
        {
            return _mediator.Send(command);
        }

        [HttpPut]
        public Task<int> UpdateGuestbook(UpdateGuestbookCommand command)
        {
            return _mediator.Send(command);
        }

        [HttpDelete("{id}")]
        public Task<int> DeleteGuestbook(int id)
        {
            return _mediator.Send(new DeleteGuestbookCommand
            {
                GuestbookId = id
            });
        }
    }
}
