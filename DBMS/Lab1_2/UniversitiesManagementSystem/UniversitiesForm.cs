using System;
using System.Data;
using System.Data.SqlClient;
using System.IO;
using System.Windows.Forms;
using System.Xml.Serialization;

namespace UniversitiesManagementSystem
{
    public partial class UniversitiesForm : Form
    {
        RelationshipDefinition RelationshipDefinition;
        SqlConnection Connection;
        SqlDataAdapter ParentAdapter, ChildAdapter;
        DataSet DataSet;
        BindingSource ParentSource, ChildSource;
        SqlCommandBuilder ChildCommandBuilder, ParentCommandBuilder;

        private void updateButton_Click(object sender, EventArgs e)
        {
            ChildAdapter.Update(DataSet, RelationshipDefinition.ChildTableName);
            ParentAdapter.Update(DataSet, RelationshipDefinition.ParentTableName);
        }

        public UniversitiesForm()
        {
            InitializeComponent();
            LoadRelationshipDefinition();
        }

        private void connectButton_Click(object sender, EventArgs e)
        {
            Connection = new SqlConnection(Constants.ConnectionString);
            DataSet = new DataSet();

            SetupAdapters();
            LoadData();

            AddFacultyUniversityRelation();
            SetupBindingSource();
            BindDataGridViews();
        }

        private void BindDataGridViews()
        {
            universitiesDataGrid.DataSource = ParentSource;
            facultiesDataGrid.DataSource = ChildSource;
        }

        private void SetupBindingSource()
        {
            ParentSource = new BindingSource();
            ChildSource = new BindingSource();

            ParentSource.DataSource = DataSet;
            ParentSource.DataMember = RelationshipDefinition.ParentTableName;

            ChildSource.DataSource = ParentSource;
            ChildSource.DataMember = RelationshipDefinition.ForeignKeyName;
        }

        private void AddFacultyUniversityRelation()
        {
            DataRelation relation = new DataRelation(RelationshipDefinition.ForeignKeyName, DataSet.Tables[RelationshipDefinition.ParentTableName].Columns[RelationshipDefinition.PrimaryKeyColumnName],
                            DataSet.Tables[RelationshipDefinition.ChildTableName].Columns[RelationshipDefinition.ForeignKeyColumnName]);

            DataSet.Relations.Add(relation);
        }

        private void LoadData()
        {
            ParentAdapter.Fill(DataSet, RelationshipDefinition.ParentTableName);
            ChildAdapter.Fill(DataSet, RelationshipDefinition.ChildTableName);
        }

        private void SetupAdapters()
        {
            ParentAdapter = new SqlDataAdapter(Constants.GetSelectFromQueryString(RelationshipDefinition.ParentTableName), Connection);
            ChildAdapter = new SqlDataAdapter(Constants.GetSelectFromQueryString(RelationshipDefinition.ChildTableName), Connection);

            ChildCommandBuilder = new SqlCommandBuilder(ChildAdapter);
            ParentCommandBuilder = new SqlCommandBuilder(ParentAdapter);
        }

        private void LoadRelationshipDefinition()
        {
            XmlSerializer serializer = new XmlSerializer(typeof(RelationshipDefinition));
            

            StreamReader reader = new StreamReader("RelationshipDefinition_.xml");
            RelationshipDefinition = new RelationshipDefinition((RelationshipDefinition)serializer.Deserialize(reader));
            reader.Close();

        }
    }
}
