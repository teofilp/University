namespace UniversitiesManagementSystem
{
    public static class Constants
    {
        public const string ConnectionString = "Data Source = EVO819L; Initial Catalog = University; Integrated Security = true;";

        public static string GetSelectFromQueryString(string tableName) => $"select* from {tableName}";

        public const string UniversitiesTableName = "Universities";

        public const string FacultiesTableName = "Faculties";

        public const string FacultiesUniveristiesForeignKey = "FK__Faculties__Unive__276EDEB3";
    }
}
