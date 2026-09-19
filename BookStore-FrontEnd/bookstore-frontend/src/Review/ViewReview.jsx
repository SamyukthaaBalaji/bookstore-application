import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
function ViewReview(){
    const {bookId}=useParams();
    const[reviews,setReviews]=useState([]);
 useEffect(() => {
    fetch(`http://localhost:8080/api/reviews/book/${bookId}`)
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
            setReviews(data);
        });
}, [bookId]);   
    return(
         <div className="container mt-5">

            <h2>Book Reviews</h2>

            {reviews.length===0 ? (

                <h5>No Reviews Yet</h5>

            ) : (

                reviews.map(review=>(

                    <div
                        className="card p-3 mb-3"
                        key={review.id}
                    >

                        <h5>{review.username}</h5>

                        <p>{"⭐".repeat(review.rating)}</p>

                        <p>{review.comment}</p>

                        <small>{review.createdAt}</small>

                    </div>

                ))

            )}

        </div>


    )
}
export default ViewReview