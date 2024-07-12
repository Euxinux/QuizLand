import React from "react";
import { Link } from "react-router-dom";
import '/src/styles/Button.css'

function Button(){
    return(
        <Link to = 'sign-up'>
            <button className="sign-up-button">Sign up</button>
        </Link>
    );
}

export default Button;