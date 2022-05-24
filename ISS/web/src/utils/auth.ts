import { Role } from "../enums/Role";

export const matchRole = (roles: Role[], role: Role) =>
  !roles || roles.includes(role);
