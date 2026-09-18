import { useContext, useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";
import UserContext from "../UserContext";

function Login() {
  const [loggedin, setLoggedin] = useContext(UserContext);
   
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate=useNavigate();
async function handleLogin(e) {
  e.preventDefault();

  try {
    const response = await fetch("http://localhost:8080/user/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        email,
        password
      })
    });

    if (response.ok) {

      const user = await response.json();
     console.log(user);
    
     localStorage.setItem("user",JSON.stringify(user))
      setLoggedin(user);

      alert("Login Successful");
      navigate("/home");
    } else {
      const message = await response.text();
      alert(message);   // "User not registered" or "Invalid password"
    }

  } catch (error) {
    console.error(error);
    alert("Something went wrong");
  }
}

  return (
    <div
      className="container d-flex justify-content-center align-items-center"
      style={{ minHeight: "90vh" }}
    >
      <div className="card shadow p-4" style={{ width: "400px" }}>
        <h2 className="text-center mb-4">Login</h2>

        <form onSubmit={handleLogin}>

          <div className="mb-3">
            <label className="form-label">
              Email
            </label>

            <input
              type="email"
              className="form-control"
              placeholder="Enter your email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>

          <div className="mb-3">
            <label className="form-label">
              Password
            </label>

            <input
              type="password"
              className="form-control"
              placeholder="Enter your password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>

          <div className="d-grid">
            <button className="btn btn-primary" type="submit">
              Login
            </button>
          </div>

        </form>

        <div className="text-center mt-3">
          <small>
            Don't have an account?{" "}
            <a href="/signup">Sign Up</a>
          </small>
        </div>
      </div>
    </div>
  );
}

export default Login;