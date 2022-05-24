import React from "react";
import { useContext } from "react";
import { Navigate, useNavigate } from "react-router-dom";
import { UserContext } from "../../context/UserContext";
import { Route } from "../../enums/Route";

export const Home = () => {
  const navigate = useNavigate();
  const { user } = useContext(UserContext);

  return user.isAuthenticated ? <>home</> : <Navigate to={Route.Login} />;
};
