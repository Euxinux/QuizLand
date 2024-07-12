import React, { useState } from "react";
import { Link } from "react-router-dom";
import Dropdown from './Dropdown'
import Button from "./Button";
import '/src/styles/NavBar.css'


function NavBar() {

    const [click, setClick] = useState(false);
    const [dropdown, setDropdown] = useState(false);
    const handleClick = () => setClick(!click);
    const closeMobileMenu = () => setClick(false);
    const onMounseEnter = () => {
        window.innerWidth < 960 ? setDropdown(false) : setDropdown(true);
    }

    const onMouseLeave = () => {
        window.innerWidth < 960 ? setDropdown(false) : setDropdown(false);
    }


    return(
        <>
            <nav className="navbar">
                <Link to= '/' className='navbar-logo' onClick={closeMobileMenu}> 
                QuizLand 
                <i class='fab fa-firstdraft' />
                </Link>
                <div className="menu-icon" onClick={handleClick}>
                    <i className= {click ? 'fas fa-times' : 'fas fa-bars'}/>
                </div>
                <ul className= {click ? 'nav-menu active' : 'nav-menu' }>
                    <li className="nav-item">
                        <Link to = '/game' className="nav-links" onClick={closeMobileMenu}>
                            Game
                        </Link>
                    </li>
                    <li className="nav-item" onMouseEnter={onMounseEnter} onMouseLeave={onMouseLeave}>
                        <Link to = '/questions' className="nav-links" onClick={closeMobileMenu} >
                            Questions <i className="fas fa-caret-down" />
                        </Link>
                        {dropdown && <Dropdown/>}
                    </li>
                    <li className="nav-item">
                        <Link to = '/options' className="nav-links" onClick={closeMobileMenu}>
                            Options
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to = '/rank' className="nav-links" onClick={closeMobileMenu}>
                            Rank
                        </Link>
                    </li>
                    <li>
                        <Link to='/sign-up' className='nav-links-mobile' onClick={closeMobileMenu}>
                            Sign Up
                        </Link>
                    </li>
                </ul>
                <Button/>
            </nav>
        </>
    );
}

export default NavBar;