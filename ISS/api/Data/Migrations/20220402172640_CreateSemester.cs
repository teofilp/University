using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Data.Migrations
{
    public partial class CreateSemester : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
               name: "Semesters",
               columns: table => new
               {
                   Id = table.Column<int>(type: "int", nullable: false)
                       .Annotation("SqlServer:Identity", "1, 1"),
                   UniversityYearID = table.Column<int>(type: "int", nullable: false),
                   SemesterDetails = table.Column<int>(type: "int", nullable: false)
               },
               constraints: table =>
               {
                   table.PrimaryKey("PK_Semesters", x => x.Id);
                   table.ForeignKey(
                        name: "FK_Semesters_UniversityYears_UniversityYearID",
                        column: x => x.UniversityYearID,
                        principalTable: "UniversityYears",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
               }
               );

            migrationBuilder.CreateIndex(
                name: "IX_Semesters_UniversityYearId",
                table: "Semesters",
                column: "UniversityYearId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Semesters");
        }
    }
}
