import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";

function SearchBook() {
    const { title } = useParams();
    const [books, setBooks] = useState([]);

    useEffect(() => {
        fetch(`http://localhost:8080/api/books/search?title=${title}`)
            .then((res) => res.json())
            .then((data) => {
                setBooks(data);
            });
    }, [title]);

    return (
        <div className="container py-5">

            {books.map((book) => (

                <div className="card shadow-lg border-0 rounded-4 mb-5" key={book.id}>

                    <div className="row g-0">

                        <div className="col-md-4 text-center p-4">

                            <img
                                src="https://images.unsplash.com/photo-1512820790803-83ca734da794?w=600"
                                alt={book.title}
                                className="img-fluid rounded shadow"
                                style={{
                                    maxHeight: "450px",
                                    objectFit: "cover"
                                }}
                            />

                        </div>

                        <div className="col-md-8">

                            <div className="card-body p-5">

                                <span className="badge bg-primary mb-3">
                                    {book.genre}
                                </span>

                                <h2 className="fw-bold mb-3">
                                    {book.title}
                                </h2>

                                <h5 className="text-secondary mb-4">
                                    {book.author}
                                </h5>

                                <hr />

                                <p>
                                    <strong>Language:</strong> {book.language}
                                </p>

                                <p>
                                    <strong>Published:</strong> {book.publishedDate}
                                </p>

                                <p>
                                    <strong>ISBN:</strong> {book.isbn}
                                </p>

                                <h3 className="text-success mt-4">
                                    ₹{book.price}
                                </h3>

                                <p className="mt-4 text-muted">
                                    {book.description}
                                </p>

                                <div className="mt-4">

                                    <button className="btn btn-success me-3">
                                        Add to Cart
                                    </button>

                                    <button className="btn btn-warning">
                                        Buy Now
                                    </button>

                                </div>

                            </div>

                        </div>

                    </div>

                </div>

            ))}

        </div>
    );
}

export default SearchBook;