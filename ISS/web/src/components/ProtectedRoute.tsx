import { useContext, PropsWithChildren } from "react";
import { UserContext } from "../context/UserContext";
import { Navigate } from "react-router-dom";
import { Role } from "../enums/Role";
import { useState } from "react";
import { matchRole } from "../utils/auth";
import { useEffect } from "react";
import { Route } from "../enums/Route";

interface ProtectedRouteProps {
  authorizedRoles: Role[];
}

export const ProtectedRoute = ({
  authorizedRoles,
  children,
}: PropsWithChildren<ProtectedRouteProps>) => {
  const { user } = useContext(UserContext);
  const [isAuthorized, setAuthorized] = useState(
    matchRole(authorizedRoles, user.role)
  );

  useEffect(() => {
    setAuthorized(matchRole(authorizedRoles, user.role));
  }, [user.role, authorizedRoles]);

  if (!user.isAuthenticated || !isAuthorized)
    return <Navigate to={Route.Home} />;

  return <>{children}</>;
};
