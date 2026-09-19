

import {createBrowserRouter,RouterProvider} from "react-router-dom"
import HomeData from "./Home/HomeData"
import Layout from "./Home/Layout"
import Booksdata from "./Books/Booksdata"
import ThemeContext from "./ThemeContext"
import { useContext, useState } from "react"
import SearchBook from "./Books/SearchBook"
import Login from "./Login/Login"
import SignUp from "./Login/SignUp"
import UserContext from "./UserContext"
import AddReview from "./Review/AddReview"
import ViewReview from "./Review/ViewReview"
import Cart from "./Cart/Cart"
import Category from "./Category/Category"
function App() {
  const[theme,setTheme]=useState("light");
  const[loggedin,setLoggedin]=useState(null);
  

  const router=createBrowserRouter([
    {
      path:"/",
      element:<Layout />,
      children:[
        {
          path:"home",
          element:<HomeData/>
        },{
          path:"books",
          element:<Booksdata/>
        },{
          path: "/search/:title",
          element: <SearchBook /> 
        },{
          path:"login",
          element:<Login/>
        },{
          path:"register",
          element:<SignUp/>
        },{
    path: "review/:bookId",
    element: <AddReview />
},
{
    path: "reviews/:bookId",
    element: <ViewReview />
},{
  path:"category/:genre",
  element:<Category/>
},{
  path:"cart",
  element:<Cart/>
}
      ]
    }
  ])
 
return(
  <ThemeContext.Provider value={[theme, setTheme]}>
    <UserContext.Provider value={[loggedin,setLoggedin]}>
      <RouterProvider router={router} />

    </UserContext.Provider>
    
</ThemeContext.Provider>

) 
}

export default App
