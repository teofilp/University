using System;
using System.Data;
using System.Data.SqlClient;
using System.IO;
using System.Windows.Forms;
using System.Xml.Serialization;

namespace UniversitiesManagementSystem
{
    public partial class AgenciesForm : Form
    {
        SqlConnection Connection;
        SqlDataAdapter ParentAdapter, ChildAdapter;
        DataSet DataSet;
        BindingSource ParentSource, ChildSource;
        SqlCommandBuilder ChildCommandBuilder, ParentCommandBuilder;

        string ChildTableName = "Buildings";
        string ParentTableName = "Agencies";
        string ForeignKeyName = "FK__Buildings__Agenc__2F10007B";
        string PrimaryKeyColumnName = "Id";
        string ForeignKeyColumnName = "AgencyId";


        private void updateButton_Click(object sender, EventArgs e)
        {
            ChildAdapter.Update(DataSet, ChildTableName);
            ParentAdapter.Update(DataSet, ParentTableName);
        }

        public AgenciesForm()
        {
            InitializeComponent();
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
            dgvAgency.DataSource = ParentSource;
            dgvBuildings.DataSource = ChildSource;
        }

        private void SetupBindingSource()
        {
            ParentSource = new BindingSource();
            ChildSource = new BindingSource();

            ParentSource.DataSource = DataSet;
            ParentSource.DataMember = ParentTableName;

            ChildSource.DataSource = ParentSource;
            ChildSource.DataMember = ForeignKeyName;
        }

        private void AddFacultyUniversityRelation()
        {
            DataRelation relation = new DataRelation(ForeignKeyName, DataSet.Tables[ParentTableName].Columns[PrimaryKeyColumnName],
                            DataSet.Tables[ChildTableName].Columns[ForeignKeyColumnName]);

            DataSet.Relations.Add(relation);
        }

        private void LoadData()
        {
            ParentAdapter.Fill(DataSet, ParentTableName);
            ChildAdapter.Fill(DataSet, ChildTableName);
        }

        private void SetupAdapters()
        {
            ParentAdapter = new SqlDataAdapter(Constants.GetSelectFromQueryString(ParentTableName), Connection);
            ChildAdapter = new SqlDataAdapter(Constants.GetSelectFromQueryString(ChildTableName), Connection);

            ChildCommandBuilder = new SqlCommandBuilder(ChildAdapter);
            ParentCommandBuilder = new SqlCommandBuilder(ParentAdapter);
        }
    }
}
