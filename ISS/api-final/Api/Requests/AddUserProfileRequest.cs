namespace Api.Requests {
    public class AddUserProfileRequest {

        public string Fullname { get; set; }

        public string Email { get; set; }

        public int Age { get; set; }

        public string ProfileImageUrl { get; set; }

        public int UserId { get; set; }
    }
}
