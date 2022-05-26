namespace Api.Requests {
    public class UpdateUserProfileRequest {
        public string Fullname { get; set; }

        public int Age { get; set; }

        public string ProfileImageUrl { get; set; }

        public int UserId { get; set; }
    }
}
