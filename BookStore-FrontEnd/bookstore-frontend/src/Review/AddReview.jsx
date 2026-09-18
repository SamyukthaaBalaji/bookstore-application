import { useState, useContext } from "react";
import { useParams, useNavigate } from "react-router-dom";
import UserContext from "../UserContext";

function AddReview() {

    const [loggedin] = useContext(UserContext);

    const { bookId } = useParams();

    const navigate = useNavigate();

    const [rating, setRating] = useState(5);
    const [comment, setComment] = useState("");

    if (!loggedin) {
        return (
            <div className="container mt-5">
                <h3>Please login first.</h3>
            </div>
        );
    }

    async function submitReview(e) {

        e.preventDefault();

        const review = {

            bookId: Number(bookId),

            userId: loggedin.id,

            username: loggedin.username,

            rating,

            comment

        };

        const response = await fetch(
            "http://localhost:8080/api/reviews",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(review)
            }
        );

        if (response.ok) {

            alert("Review Added Successfully");

            navigate(`/reviews/${bookId}`);

        } else {

            alert("Failed to add review");

        }

    }

    return (
        <div className="container mt-5">

            <div className="card p-4">

                <h2>Add Review</h2>

                <p>
                    Reviewing as <strong>{loggedin.username}</strong>
                </p>

                <form onSubmit={submitReview}>

                    <label className="form-label">Rating</label>

                    <select
                        className="form-select mb-3"
                        value={rating}
                        onChange={(e) => setRating(Number(e.target.value))}
                    >
                        <option value="1">⭐</option>
                        <option value="2">⭐⭐</option>
                        <option value="3">⭐⭐⭐</option>
                        <option value="4">⭐⭐⭐⭐</option>
                        <option value="5">⭐⭐⭐⭐⭐</option>
                    </select>

                    <label className="form-label">Comment</label>

                    <textarea
                        className="form-control mb-3"
                        rows="5"
                        value={comment}
                        onChange={(e) => setComment(e.target.value)}
                    />

                    <button className="btn btn-success">
                        Submit Review
                    </button>

                </form>

            </div>

        </div>
    );
}

export default AddReview;