import { useEffect, useState, useContext } from "react";
import { useParams } from "react-router-dom";
import ThemeContext from "../ThemeContext";
import Books from "../Books/Books";

function Category() {

    const { genre } = useParams();

    const [theme] = useContext(ThemeContext);

    const [books, setBooks] = useState([]);

    useEffect(() => {

        fetch(`http://localhost:8080/api/books/genre/${genre}`)
            .then((res) => res.json())
            .then((data) => {
                console.log(data);
                setBooks(data);
            })
            .catch((error) => {
                console.log("Error fetching books:", error);
            });

    }, [genre]);

    return (

        <div
            className={
                theme === "light"
                    ? "bg-light text-dark min-vh-100"
                    : "bg-dark text-light min-vh-100"
            }
        >

            <div className="container mt-4">

                <h2 className="mb-4">
                    {genre} Books
                </h2>

                <div className="row">

                    {books.length > 0 ? (

                        books.map((b) => (

                            <Books
                                key={b.id}
                                id={b.id}
                                author={b.author}
                                description={b.description}
                                genre={b.genre}
                                isbn={b.isbn}
                                language={b.language}
                                price={b.price}
                                published_date={b.published_date}
                                title={b.title}
                            />

                        ))

                    ) : (

                        <p>No books found for this genre.</p>

                    )}

                </div>

            </div>

        </div>
    );
}

export default Category;