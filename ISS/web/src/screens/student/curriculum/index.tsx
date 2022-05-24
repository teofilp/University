import { Button, Col, Row, Select, Table } from "antd";
import React, { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../../../api";
import { ApiEndpoints } from "../../../api/endpoints";
import { UserContext } from "../../../context/UserContext";
import { Route } from "../../../enums/Route";
import { AnnualContractDetails } from "../../../models/AnnualContractDetails";
import { ContractDiscipline } from "../../../models/ContractDiscipline";
import {
  getOptionalDisciplinesColumns,
  getSelectedDisciplinesColumns,
} from "./columns";
import "./main.css";

export const CurriculumContent = () => {
  const navigate = useNavigate();
  const { user } = useContext(UserContext);
  const [selectedDisciplines, setSelectedDisciplines] = useState<
    ContractDiscipline[]
  >([]);
  const [inactiveDisciplines, setInactiveDisciplines] = useState<
    ContractDiscipline[]
  >([]);
  const [year, setYear] = useState<number | undefined>();
  const [yearsOptions, setYearsOptions] = useState<
    { value: number; label: number }[]
  >([]);
  const [annualContractDetails, setAnnualContractDetails] = useState<
    AnnualContractDetails[]
  >([]);
  const [isLoading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    api
      .get<AnnualContractDetails[]>(`${ApiEndpoints.contract.base}/${user.id}`)
      .then(({ data }) => {
        setAnnualContractDetails(data);
        setYearsOptions(
          data.map((x) => ({
            value: x.year,
            label: x.year,
          }))
        );
        setYear(data[0]?.year);
        setSelectedDisciplines(data[0]?.mandatoryDisciplines || []);
        setInactiveDisciplines(data[0]?.optionalDisciplines || []);
      })
      .finally(() => setLoading(false));
  }, []);

  const handleRemoveDiscipline = (discipline: ContractDiscipline) => {
    setSelectedDisciplines(
      selectedDisciplines.filter((x) => x.courseId !== discipline.courseId)
    );
    setInactiveDisciplines([...inactiveDisciplines, discipline]);
  };

  const handleAddDiscipline = (discipline: ContractDiscipline) => {
    setInactiveDisciplines(
      inactiveDisciplines.filter((x) => x.courseId !== discipline.courseId)
    );
    setSelectedDisciplines([...selectedDisciplines, discipline]);
  };

  useEffect(() => {
    if (!year) return;
    const disciplinesForYear = annualContractDetails.find(
      (x) => x.year === year
    );
    setInactiveDisciplines(disciplinesForYear!.optionalDisciplines);
    setSelectedDisciplines(disciplinesForYear!.mandatoryDisciplines);
  }, [year]);

  const handleSubmit = () => {
    const request = {
      year,
      courseIds: selectedDisciplines.map((x) => x.courseId),
      userId: user.id,
    };

    api
      .post(ApiEndpoints.contract.base, request)
      .then(() => {
        alert("contract added sucessfully");
      })
      .then(() => navigate(Route.Contracts))
      .catch((e) => console.log(e));
  };

  return (
    <div className="site-layout-content">
      <span className="label">Select contract year</span>
      <Select
        className="year-select"
        value={year}
        onChange={(value) => setYear(value)}
        options={yearsOptions}
        loading={isLoading}
      />
      <Row>
        <Col span={11}>
          <h2>Selected disciplines</h2>
          <Table
            className="table-content"
            dataSource={selectedDisciplines}
            columns={getSelectedDisciplinesColumns(handleRemoveDiscipline)}
            pagination={false}
            loading={isLoading}
          />
        </Col>
        <Col offset={2} span={11}>
          <h2>Optional disciplines</h2>
          <Table
            className="table-content"
            dataSource={inactiveDisciplines}
            columns={getOptionalDisciplinesColumns(handleAddDiscipline)}
            pagination={false}
            loading={isLoading}
          />
        </Col>
      </Row>
      <Row style={{ marginTop: 32 }}>
        <Col offset={18} span={6}>
          <Button
            type="primary"
            style={{ float: "right" }}
            onClick={handleSubmit}
            disabled={!year}
          >
            Add contract
          </Button>
        </Col>
      </Row>
    </div>
  );
};
