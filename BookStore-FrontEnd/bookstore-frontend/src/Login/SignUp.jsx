import { useState } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";


function SignUp() {
  
  const[username,setUsername]=useState("");
  const[email,setEmail]=useState("");
  const[password,setPassword]=useState("");
  const navigate=useNavigate();
  
 async function handleSubmit(e) {
  e.preventDefault();

  const newUser = {
    username,
    email,
    password
  };

  try {
    const response = await fetch("http://localhost:8080/user/signup", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(newUser)
    });

    if (response.ok) {
      alert("Registration Successful");
      navigate("/login");
      
    } else {
      const message = await response.text();
      alert(message);
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
      <div className="card shadow p-4" style={{ width: "420px" }}>
        <h2 className="text-center mb-4">Create Account</h2>

        <form onSubmit={handleSubmit}>

          <div className="mb-3">
            <label className="form-label">Username</label>

            <input
              type="text"
              className="form-control"
              name="username"
              value={username}
              onChange={(e)=>setUsername(e.target.value)}
             
              placeholder="Enter username"
              required
            />
          </div>

          <div className="mb-3">
            <label className="form-label">Email</label>

            <input
              type="email"
              className="form-control"
              name="email"
                 value={email}
              onChange={(e)=>setEmail(e.target.value)}
             
              placeholder="Enter email"
              required
            />
          </div>

          <div className="mb-3">
            <label className="form-label">Password</label>

            <input
              type="password"
              className="form-control"
              name="password"
                 value={password}
              onChange={(e)=>setPassword(e.target.value)}
              
              placeholder="Enter password"
              required
            />
          </div>

          <div className="d-grid">
            <button className="btn btn-success">
              Register
            </button>
          </div>

        </form>

        <div className="text-center mt-3">
          Already have an account?{" "}
          <Link to="/login">Login</Link>
        </div>

      </div>
    </div>
  );
}

export default SignUp;