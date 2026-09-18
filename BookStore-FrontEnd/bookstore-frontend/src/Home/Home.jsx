import BookImage from "../assets/stack-books-removebg-preview.png";
import { Link } from "react-router-dom";
import "./Home.module.css";

function Home() {
  return (
    <>
      {/* Hero Section */}
      <div className="hero container-fluid">
        <div className="row align-items-center">

          <div className="col-lg-6 text-center">
            <img
              src={BookImage}
              alt="Books"
              className="hero-image"
            />
          </div>

          <div className="col-lg-6 hero-content">
            <h1>
              A Room Without Books
              <br />
              Is Like A Body
              <br />
              Without A Soul.
            </h1>

            <p>
              Discover thousands of books across programming,
              fiction, history, science, and many more.
              Read. Learn. Grow.
            </p>

            <Link to="/books">
              <button className="btn btn-primary btn-lg">
                Browse Books
              </button>
            </Link>
          </div>

        </div>
      </div>

      {/* Footer */}
      <footer className="bg-dark text-light mt-5 py-4">
        <div className="container">

          <div className="row">

            <div className="col-md-4">
              <h5>📚 BookStore</h5>
              <p>
                Your one-stop destination for discovering and exploring books
                across various genres.
              </p>
            </div>

            <div className="col-md-4">
              <h5>Quick Links</h5>

              <ul className="list-unstyled">
                <li>
                  <Link to="/home" className="text-light text-decoration-none">
                    Home
                  </Link>
                </li>

                <li>
                  <Link to="/books" className="text-light text-decoration-none">
                    Books
                  </Link>
                </li>

                <li>
                  <Link to="/login" className="text-light text-decoration-none">
                    Login
                  </Link>
                </li>
              </ul>
            </div>

            <div className="col-md-4">
              <h5>Contact</h5>

              <p>📧 support@bookstore.com</p>
              <p>📞 +91 98765 43210</p>
              <p>📍 Chennai, India</p>
            </div>

          </div>

          <hr className="border-light" />

          <div className="text-center">
            © 2026 BookStore. All Rights Reserved.
          </div>

        </div>
      </footer>
    </>
  );
}

export default Home;