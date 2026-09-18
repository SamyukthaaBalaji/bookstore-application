import { useNavigate } from "react-router-dom";
import UserContext from "../UserContext";
import { useContext } from "react";

function Books(props) {
     const navigate = useNavigate();
     const [loggedin] = useContext(UserContext);
     async function addtocart(bookId){
       if (!loggedin) {
        alert("Please login first");
        navigate("/login");
        return;
    }
    const cart={
      userId:loggedin.id,
      bookId:bookId,
      quantity:1
    }
      const response = await fetch("http://localhost:8080/api/cart", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(cart)
    });

    if(response.ok){
        alert("Book added to cart");
        navigate("/cart")

    }

      
  }

  return (
    <>
    
      <div className="col-md-4 mb-4">
        <div className="card shadow h-100">

              <img
          src="https://images.unsplash.com/photo-1512820790803-83ca734da794"
          className="card-img-top"
          alt="Book"
          style={{ height: "350px", objectFit: "cover" }}
          />

          <div className="card-body">
            <h4 className="card-title">{props.title}</h4>

            <p className="card-text">
              <strong>Author:</strong> {props.author}
            </p>

            <p className="card-text">
              <strong>Price:</strong> ₹{props.price}
            </p>
              <button className="btn btn-success me-2"  onClick={()=> navigate(`/review/${props.id}`)}>
                      Add an review
                    </button>

            <button
              className="btn btn-primary w-100"
              data-bs-toggle="modal"
              data-bs-target={`#bookDetails${props.id}`}
            >
              View Details
            </button>
          </div>
        </div>

        {/* Modal */}
        <div
          className="modal fade"
          id={`bookDetails${props.id}`}
          tabIndex="-1"
          aria-hidden="true"
        >
          <div className="modal-dialog modal-lg">
            <div className="modal-content">

              <div className="modal-header">
                <h5 className="modal-title">Book Details</h5>

                <button
                  type="button"
                  className="btn-close"
                  data-bs-dismiss="modal"
                ></button>
              </div>

              <div className="modal-body">
                <div className="row">

                  <div className="col-md-4">
                    <img
                      src="https://images.unsplash.com/photo-1512820790803-83ca734da794"
                      className="img-fluid rounded"
                      alt="Book"
                    />
                  </div>

                  <div className="col-md-8">
                    <h3>{props.title}</h3>

                    <p>
                      <strong>Author:</strong> {props.author}
                    </p>

                    <p>
                      <strong>Genre:</strong> {props.genre}
                    </p>

                    <p>
                      <strong>Language:</strong> {props.language}
                    </p>

                    <p>
                      <strong>Price:</strong> ₹{props.price}
                    </p>

                    <p>
                      <strong>Description:</strong> {props.description}
                    </p>

                   <button
    className="btn btn-success me-2"
    data-bs-dismiss="modal"
    onClick={() => addtocart(props.id)}
>
    Add to Cart
</button>
                    
                     <button className="btn btn-success me-2" data-bs-dismiss="modal" onClick={()=> navigate(`/reviews/${props.id}`)}>
                     View review
                    </button>
                    

                    <button className="btn btn-warning">
                      Buy Now
                    </button>
                  </div>

                </div>
              </div>

            </div>
          </div>
        </div>

      </div>
    </>
  );
}

export default Books;