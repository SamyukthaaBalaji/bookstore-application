import { useContext, useEffect, useState } from "react";
import UserContext from "../UserContext";

function Cart() {
  const [cartItems, setCartItems] = useState([]);
  const [loggedin] = useContext(UserContext);

  useEffect(() => {
    if (!loggedin) return;

    fetch(`http://localhost:8080/api/cart/${loggedin.id}`)
      .then((res) => res.json())
      .then((data) => setCartItems(data));
  }, [loggedin]);

  return (
    <div className="container py-5">
      <h2 className="text-center mb-4 fw-bold">
        🛒 My Shopping Cart
      </h2>

      {cartItems.length === 0 ? (
        <div className="alert alert-info text-center fs-5">
          Your cart is empty.
        </div>
      ) : (
        <div className="row g-4">
          {cartItems.map((item) => (
            <div className="col-lg-6" key={item.cartId}>
              <div className="card shadow-lg border-0 rounded-4 h-100">
                <div className="row g-0">

                  <div className="col-md-4">
                    <img
                      src="https://images.unsplash.com/photo-1512820790803-83ca734da794"
                      className="img-fluid rounded-start h-100"
                      alt="Book"
                      style={{ objectFit: "cover" }}
                    />
                  </div>

                  <div className="col-md-8">
                    <div className="card-body">

                      <h4 className="card-title fw-bold">
                        {item.book.title}
                      </h4>

                      <p className="mb-2">
                        <strong>Author :</strong> {item.book.author}
                      </p>

                      <p className="mb-2">
                        <strong>Price :</strong>{" "}
                        <span className="text-success fw-bold">
                          ₹{item.book.price}
                        </span>
                      </p>

                      <p className="mb-3">
                        <strong>Quantity :</strong>{" "}
                        <span className="badge bg-primary fs-6">
                          {item.quantity}
                        </span>
                      </p>

                      <div className="d-flex gap-2">
                        <button className="btn btn-outline-danger">
                          Remove
                        </button>

                        <button className="btn btn-success">
                          Buy Now
                        </button>
                      </div>

                    </div>
                  </div>

                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default Cart;