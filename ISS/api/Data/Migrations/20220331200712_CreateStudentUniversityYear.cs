using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Data.Migrations
{
    public partial class CreateStudentUniversityYear : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "StudentUniversityYears",
                columns: table => new
                {
                    Id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    StudentId = table.Column<int>(type: "int", nullable: false),
                    UniversityYearId = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_StudentUniversityYears", x => x.Id);
                    table.ForeignKey(
                        name: "FK_StudentUniversityYears_Student_StudentId",
                        column: x => x.StudentId,
                        principalTable: "Students",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_StudentUniversityYears_UniversityYears_UniversityYearId",
                        column: x => x.UniversityYearId,
                        principalTable: "UniversityYears",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_StudentUniversityYears_StudentId",
                table: "StudentUniversityYears",
                column: "StudentId");

            migrationBuilder.CreateIndex(
                name: "IX_StudentUniversityYears_UniversityYearId",
                table: "StudentUniversityYears",
                column: "UniversityYearId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "StudentUniversityYears");
        }
    }
}
