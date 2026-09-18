import { Link, useLocation, useNavigate } from "react-router-dom";
import { useContext, useState } from "react";
import ThemeContext from "../ThemeContext";
import UserContext from "../UserContext";

function Navbar() {

  const [theme] = useContext(ThemeContext);

  const [loggedin, setLoggedin] = useContext(UserContext);

  const [search, setSearch] = useState("");

  const navigate = useNavigate();

  const location = useLocation();
  const hideNavItems =
  location.pathname === "/login" ||
  location.pathname === "/register";

  function handleSearch(e) {

    e.preventDefault();

    if (search.trim() !== "") {

      navigate(`/search/${search}`);

    }

  }

  function handleLogout() {

    setLoggedin(null);

    navigate("/login");

  }

  return (

    <nav
      className={
        theme === "light"
          ? "navbar navbar-expand-lg navbar-light bg-light"
          : "navbar navbar-expand-lg navbar-dark bg-dark"
      }
    >

      <div className="container">

        <Link className="navbar-brand fw-bold" to="/home">
          📚 BookStore
        </Link>

        <span className="me-3 fw-bold">
          Hello {loggedin ? loggedin.username : "Guest"}
        </span>

        <button
          className="navbar-toggler"
          data-bs-toggle="collapse"
          data-bs-target="#navbar"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navbar">

         <ul className="navbar-nav me-auto">

  {!hideNavItems && (
    <>
      <li className="nav-item">
        <Link className="nav-link" to="/home">
          Home
        </Link>
      </li>

      <li className="nav-item dropdown">
        <a
          className="nav-link dropdown-toggle"
          href="#"
          role="button"
          data-bs-toggle="dropdown"
        >
          Categories
        </a>

        <ul className="dropdown-menu">
          <li><Link className="dropdown-item" to="/category/programming">Programming</Link></li>
          <li><Link className="dropdown-item" to="/category/fiction">Fiction</Link></li>
          <li><Link className="dropdown-item" to="/category/history">History</Link></li>
          <li><Link className="dropdown-item" to="/category/science">Science</Link></li>
        </ul>
      </li>
    </>
  )}

</ul>

          {location.pathname === "/home" && (

            <form className="d-flex me-3" onSubmit={handleSearch}>

              <input
                className="form-control me-2"
                placeholder="Search Books"
                value={search}
                onChange={(e) => setSearch(e.target.value)}
              />

              <button className="btn btn-success">
                Search
              </button>

            </form>

          )}

          <div className="dropdown">

            <button
              className="btn btn-outline-secondary rounded-circle"
              data-bs-toggle="dropdown"
            >
              👤
            </button>

            <ul className="dropdown-menu dropdown-menu-end">

              {!loggedin ? (

                <>

                  <li>

                    <Link
                      className="dropdown-item"
                      to="/login"
                    >
                      Login
                    </Link>

                  </li>

                  <li>

                    <Link
                      className="dropdown-item"
                      to="/register"
                    >
                      Signup
                    </Link>

                  </li>

                </>

              ) : (

                <>

                  <li>
                     <span className="dropdown-item-text">

                    {loggedin.username}

                    </span>



                  </li>

                  <li><hr className="dropdown-divider" /></li>
                 

                  <li>

                    <button
                      className="dropdown-item"
                      onClick={handleLogout}
                    >
                      Logout
                    </button>

                  </li>

                </>

              )}

            </ul>

          </div>

        </div>

      </div>

    </nav>

  );

}

export default Navbar;