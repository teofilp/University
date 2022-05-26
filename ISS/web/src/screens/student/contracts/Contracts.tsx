import { Select } from "antd";
import { useContext, useEffect, useState } from "react";
import api from "../../../api";
import { ApiEndpoints } from "../../../api/endpoints";
import { PdfTable } from "../../../components/pdf-table/PdfTable";
import { UserContext } from "../../../context/UserContext";
import { StudentContract } from "../../../models/StudentContract";
import { columns } from "./columns";

export const Contracts = () => {
  const { user } = useContext(UserContext);
  const [contracts, setContracts] = useState<StudentContract[]>([]);
  const [year, setYear] = useState<number | undefined>();
  const [yearOptions, setYearOptions] = useState<
    {
      label: string;
      value: number;
    }[]
  >([]);
  const activeContract =
    contracts.find((x) => x.year === year)?.contractDisciplineDetails || [];

  useEffect(() => {
    api
      .get<StudentContract[]>(
        `${ApiEndpoints.contract.getStudentContracts}/${user.id}`
      )
      .then(({ data }) => {
        setContracts(data);
        setYearOptions(
          data.map((x) => ({
            value: x.year,
            label: `Year ${x.year}`,
          }))
        );
        setYear(data[0]?.year);
      });
  }, []);

  return (
    <>
      <span className="label">Select university year</span>
      <Select value={year} options={yearOptions} onChange={setYear} />
      <PdfTable
        orientation="landscape"
        columns={columns}
        dataSource={activeContract}
        title={year ? `University year ${year} contract` : ""}
      />
    </>
  );
};
