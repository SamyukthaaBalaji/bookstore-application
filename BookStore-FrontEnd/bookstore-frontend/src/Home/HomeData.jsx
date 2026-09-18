import { useContext, useEffect,useState } from "react"
import { useParams } from "react-router-dom";
import Home from "./Home"
import GenreCard from "./GenreCard";
import ThemeContext from "../ThemeContext";

function HomeData(){
    const[theme,setTheme]=useContext(ThemeContext)
     const { genre } = useParams();
    const[genres,setGenres]=useState([]);
    useEffect(() => {
    fetch("http://localhost:8080/api/books/allgenres")
        .then(res => res.json())
        .then(data => {
            console.log(data);
            setGenres(data)});
}, []);
    return(
        <>
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
                    
        <Home/>
       
      
        
       
       
         </div>
        
      
        </>

    )
}
export default HomeData