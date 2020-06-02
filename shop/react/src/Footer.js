
import { Link } from "react-router-dom";
import React from 'react';

function Footer(props) {
  if(props.isAdmin){
    return (
      <footer className="flex center">
          <span className="col1 center">
              online-shop - інтернет-магазин
          </span>
          <span className="col1 center">
            <Link to="/additem">
              Додати товар
            </Link>
          </span>
          
  
      </footer>
    );
  }else{
    return (
      <footer className="flex center">
          <span className="col1 center">
              online-shop - інтернет-магазин
          </span>
      </footer>
    );
  }
  
}

export default Footer;
