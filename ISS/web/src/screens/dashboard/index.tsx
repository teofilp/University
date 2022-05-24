import React from "react";
import { Profile } from "./UserProfile";
import { Information } from "./InfoCard";
import { StudentCertificate } from "./StudentCertificateCard";
import "./main.css";

export const Dashboard = () => {
  return (
    <div style={{ display: "flex" }}>
      <Profile />
      <Information />
      <StudentCertificate />
    </div>
  );
};
