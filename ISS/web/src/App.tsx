import React, { useState, Fragment } from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route as RouteComponent,
} from "react-router-dom";
import { ToastContainer } from "react-toastify";
import { Layout } from "./components/layout";
import { ProtectedRoute } from "./components/ProtectedRoute";
import { UserContext, getDefaultUser } from "./context/UserContext";
import { Route as RouteModel } from "./models/Route";
import { User } from "./models/User";
import { routes } from "./routes";

const renderRoute = ({
  path,
  component: Component,
  isProtected,
  authorizedRoles,
}: RouteModel) =>
  isProtected ? (
    <RouteComponent
      path={path}
      element={
        <ProtectedRoute authorizedRoles={authorizedRoles!}>
          <Component />
        </ProtectedRoute>
      }
      key={path}
    />
  ) : (
    <RouteComponent key={path} path={path} element={<Component />} />
  );

const App = () => {
  const [user, setUser] = useState<User>(getDefaultUser());
  const LayoutComponent = user.isAuthenticated ? Layout : Fragment;

  const handleLogout = () => {
    localStorage.clear();
    setUser(getDefaultUser());
  };

  return (
    <Router>
      <UserContext.Provider value={{ user, setUser, logout: handleLogout }}>
        <LayoutComponent>
          <Routes>
            {routes.map((route: RouteModel) => renderRoute(route))}
          </Routes>
        </LayoutComponent>
      </UserContext.Provider>
      <ToastContainer />
    </Router>
  );
};

export default App;
