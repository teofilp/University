using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Data.Migrations
{
    public partial class createCourseEnrolment : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "CoursesEnrolments",
                columns: table => new
                {
                    Id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    StudentUniversityYearID = table.Column<int>(type: "int", nullable: false),
                    CoursesID = table.Column<int>(type: "int", nullable: false),
                    Grade = table.Column<float>(type: "real", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CoursesEnrolments", x => x.Id);
                    table.ForeignKey(
                        name: "FK_CoursesEnrolments_Courses_CoursesID",
                        column: x => x.CoursesID,
                        principalTable: "Courses",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_CoursesEnrolments_StudentUniversityYears_StudentUniversityYearID",
                        column: x => x.StudentUniversityYearID,
                        principalTable: "StudentUniversityYears",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateIndex(
                name: "IX_CoursesEnrolments_CoursesID",
                table: "CoursesEnrolments",
                column: "CoursesID");

            migrationBuilder.CreateIndex(
                name: "IX_CoursesEnrolments_StudentUniversityYearID",
                table: "CoursesEnrolments",
                column: "StudentUniversityYearID");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "CoursesEnrolments");
        }
    }
}
