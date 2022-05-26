import React, { useState } from "react";
import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import Avatar from "../../assets/avatar.jpg";
import { UserContext } from "../../context/UserContext";
import { Route } from "../../enums/Route";
import UserService from "../../services/UserService";

import "./main.css";

export const Login = () => {
  const { setUser, user } = useContext(UserContext);
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const submitHandler = (e: any) => {
    e.preventDefault();
    UserService.login(username, password)
      .then((data) => {
        console.log(data);
        setUser({
          ...user,
          isAuthenticated: true,
          role: data.role,
          id: data.userId,
        });
        navigate(Route.Home);
      })
      .catch(() => {
        toast("Login failed! Please check your credentials and try again", {
          type: "error",
        });
      });
  };

  return (
    <div id="form-container">
      <form className="loginForm" onSubmit={submitHandler}>
        <div className="imgcontainer">
          <img src={Avatar} alt="Avatar" className="avatar" />
        </div>
        <label htmlFor="uname">
          <b>Username</b>
        </label>
        <input
          className="login-item"
          type="text"
          value={username}
          name="username"
          placeholder="Enter Username"
          required
          onChange={(e) => setUsername(e.target.value)}
        />
        <label htmlFor="psw">
          <b>Password</b>
        </label>
        <input
          className="login-item"
          type="password"
          value={password}
          name="password"
          placeholder="Enter Password"
          required
          onChange={(e) => setPassword(e.target.value)}
        />

        <button id="login-button" type="submit">
          Log In
        </button>
      </form>
    </div>
  );
};
