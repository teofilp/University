import React from "react";
import { Role } from "../enums/Role";

export interface Route {
  path: string;
  component: React.FunctionComponent;
  isProtected?: boolean;
  authorizedRoles?: Role[];
}
