import { useNavigate } from "react-router-dom";
function GenreCard(props){
    const navigate = useNavigate();
    return(
         <div className="col-md-3 mb-4">

            <div className="card shadow text-center h-100">

                <div className="card-body">

                    <h4>{props.genre}</h4>

                    <button
                        className="btn btn-primary mt-3"
                        onClick={() => navigate(`/books/${props.genre}`)}
                    >
                        Click Here
                    </button>

                </div>

            </div>

        </div>

    )
}

export default GenreCard