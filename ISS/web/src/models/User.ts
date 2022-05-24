import { Role } from "../enums/Role";

export interface User {
  isAuthenticated: boolean;
  id: number;
  email: string;
  role: Role;
  displayName: string;
}
