import { createContext } from "react";
import { Role } from "../enums/Role";
import { User } from "../models/User";

interface UserContextModel {
  user: User;
  setUser: (user: User) => void;
  logout: () => void;
}

export const getDefaultUser = (): User => ({
  id: Number(localStorage.getItem("userId")) ?? 0,
  role: Number(localStorage.getItem("role")) ?? Role.Student,
  displayName: "",
  email: "",
  isAuthenticated: localStorage.getItem("userId") !== null,
});

export const getDefaultUserContextModel = (): UserContextModel => ({
  setUser: () => {},
  logout: () => {},
  user: getDefaultUser(),
});

export const UserContext = createContext<UserContextModel>(
  getDefaultUserContextModel()
);
