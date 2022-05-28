namespace UniversitiesManagementSystem
{
    public static class Utils
    {
        public static string GetCleanString(string str)
        {
            str = str.Replace("\t", "").Replace("\n", "");

            return str;
        }
    }
}
