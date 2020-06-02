import React from 'react';
import { Link } from "react-router-dom";
const axios = require('axios');


class Respond extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          booking: null
        };
       
    }
    componentDidMount() {

      axios.get('http://localhost:8080/booking/' + this.props.feedback.bookingId )
      .then(res => {
        this.setState({booking: res.data});
      }).then(res=>res.json())
      .catch((err, res)=>{
        
      });

    }
    render()
    {   
        if(this.state.booking){
            return(
            
                <div className="col1 flex advice">

                    
                    <div className="text col5">
                    <h3>Користувач:{this.state.booking.user.name}</h3>
                    <p>
                            Написав:{this.props.feedback.text}
                        </p>
                        <span>Дата:{this.props.feedback.date}</span>
                        <br></br>
                        <mark>Оцінка:{this.props.feedback.rating}</mark>

                        
                    </div>

                </div>
        
        )
        }else{
           return  null
        }

        
            

    }

}

export default Respond;
