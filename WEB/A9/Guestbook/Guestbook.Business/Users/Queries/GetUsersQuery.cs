using Guestbook.Data.Models;
using MediatR;

namespace Guestbook.Business.Users.Queries;

public record GetUsersQuery: IRequest<List<User>>
{ }