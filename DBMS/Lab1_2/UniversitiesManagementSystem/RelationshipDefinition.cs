namespace UniversitiesManagementSystem
{
    public class RelationshipDefinition
    {
        public string ParentTableName { get; set; }
        public string ChildTableName { get; set; }
        public string ForeignKeyName { get; set; }
        public string PrimaryKeyColumnName { get; set; }
        public string ForeignKeyColumnName { get; set; }

        public RelationshipDefinition() { }

        public RelationshipDefinition(RelationshipDefinition old)
        {
            ParentTableName = Utils.GetCleanString(old.ParentTableName);
            ChildTableName = Utils.GetCleanString(old.ChildTableName);
            ForeignKeyName = Utils.GetCleanString(old.ForeignKeyName);
            PrimaryKeyColumnName = Utils.GetCleanString(old.PrimaryKeyColumnName);
            ForeignKeyColumnName = Utils.GetCleanString(old.ForeignKeyColumnName);
        } 
    }
}
