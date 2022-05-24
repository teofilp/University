namespace Api.Requests
{
    public class AddOptionalCourseRequest
    {
        public string Name { get; set; }
        public int SemesterId { get; set; }
        public int SpecializationId { get; set; }
        public int UserId { get; set; }

    }
}
