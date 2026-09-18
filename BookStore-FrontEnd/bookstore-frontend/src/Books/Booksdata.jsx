import { useState,useEffect } from "react"
import { useContext } from "react";
import ThemeContext from "../ThemeContext";

import Books from "./Books"
function Booksdata(){
   const[theme,setTheme]=useContext(ThemeContext)
    const[books,setBooks]=useState([]);
    const [currentPage, setCurrentPage] = useState(0);
const [totalPages, setTotalPages] = useState(0);
const [pageSize] = useState(6); // 6 books per page
   
   const[filterbyauthor,setFilterbyauthor]=useState([]);
   useEffect(() => {
    fetch(`http://localhost:8080/api/books?page=${currentPage}&size=${pageSize}`)
        .then((res) => res.json())
        .then((data) => {
            console.log(data);

            setBooks(data.content);
            setTotalPages(data.totalPages); // Important
        });
}, [currentPage, pageSize]);
    function  sortAToZ(){
      const sortbooks=[...books];
      sortbooks.sort((a,b)=>{
        if(a.title>b.title){
          return 1;
        }else if (a.title<b.title){
          return -1;
        }else{
          return 0;
        }
      })
      setBooks(sortbooks);
      setTotalPages(data.totalPages);
    }

    function sortLowToHigh(){
      const lowratebooks=[...books];
      lowratebooks.sort((a,b)=>{
        if(a.price>b.price){
          return 1;
        }else if(a.price<b.price){
          return -1;
        }else{
          return 0;
        }
      })
      setBooks( lowratebooks);

    } 
    function sortHighToLow(){
      const highprice=[...books];
      highprice.sort((a,b)=>{
        if(a.price<b.price){
          return 1;
        }else if(a.price>b.price){
          return -1;
        }else{
          return 1;
        }
      })
      setBooks(highprice)
    }
   
    

   

    return(
        <>
         {/* Filter Section */}
     {/* Filter Dropdown */}
     <div className={theme === "light" ? "bg-light text-dark min-vh-100" : "bg-dark text-light min-vh-100"}>
 <button
                        className={
                            theme === "light"
                                ? "btn btn-dark"
                                : "btn btn-light"
                        }
                        onClick={() =>
                            setTheme(theme === "light" ? "dark" : "light")
                        }
                    >
                        {theme === "light"
                            ? "🌙 Dark Mode"
                            : "☀️ Light Mode"}
                    </button>
                    <div className="container mt-4 mb-4">

    <div className="row justify-content-end">

        <div className="col-md-4">

           <select
    className="form-select"
    onChange={(e) => {
        if (e.target.value === "author") {
            authorFilter();
        } else if (e.target.value === "low") {
            sortLowToHigh();
        } else if (e.target.value === "high") {
            sortHighToLow();
        } else if (e.target.value === "az") {
            sortAToZ();
        }
    }}
>
    <option value="">Filter Books</option>
  
    <option value="low">Price: Low → High</option>
    <option value="high">Price: High → Low</option>
    <option value="az">A → Z</option>
</select>
 

        </div>
       

    </div>

</div>

    <div className="container mt-4">
    <div className="row">
        {books.map((b) => (
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
        ))}
    </div>
</div>
<div className="d-flex justify-content-center mt-4">

    {
        [...Array(totalPages)].map((_, index) => (
                                                                                                              
            <button
                key={index}
                className={
                    currentPage === index
                        ? "btn btn-primary mx-1"
                        : "btn btn-outline-primary mx-1"
                }
                onClick={() => setCurrentPage(index)} 
            >
                {index + 1}
            </button>

        ))
    }

</div>
                    </div>


      
        </>
    )
}
export default Booksdata