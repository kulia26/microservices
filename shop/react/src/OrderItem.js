import React from 'react';
import { Link } from "react-router-dom";
const axios = require('axios');

class OrderItem extends React.Component {
    constructor(props) {
        super(props);
        let user = sessionStorage.getItem('user');
        user = user ? JSON.parse(user) : {};
        this.state = {
          item: this.props.el,
          user: user,
          email: '', 
          name: ''
        };
        this.deleteThisItem = this.deleteThisItem.bind(this);
        this.getDate = this.getDate.bind(this);
        this.getAddRespond = this.getAddRespond.bind(this);
    }

    getAddRespond() {
      if(!this.props.isAdmin){
          return(
              <>
              <div className="buttons">
                <div className="button">
                <Link to={"/addRespond/"+this.props.item.id}>
                 <button >Додати відгук</button>
                 </Link>
               </div>
              </div>
              </>
            )
      }
  }

    deleteThisItem() {
      const item = this.props.item;
      const url = 'http://localhost:8080/booking/'+item.id;
      let token = JSON.parse(sessionStorage.getItem('token'));
      if(token){
        const config = {
            headers: {
               "Authorization" : 'Bearer '+token,
               "Content-Type":"application/json"
            },  
        }
        axios
        .delete(url, config)
        .then((res) => {
          this.setState({ message : res.data.message});
          this.props.onDelete(item);
        })
        .catch(err => this.setState({ message :  err.response.message}));
      }else{
        window.alert('Ви не авторизовані !');
      }
     }

     

    getDeleteButton() {

        if( this.props.isAdmin){
          return(
            <>
            <div className="buttons">
              <div className="button">
              <button onClick={this.deleteThisItem}><span >🗑️</span>Видалити</button>
              </div>
            </div>       
            </>
          )
        }
    }

   getDate(){
      let thisDate = new Date();
      thisDate.setTime(this.props.time);
      let date = thisDate.getDate()+'.'
      +thisDate.getMonth()+'.'+
      thisDate.getFullYear()+' '+
      thisDate.getHours()+':'+thisDate.getMinutes()+'';
      return date;
   }

   componentDidMount(){
    const url = 'http://localhost:8080/booking/'+this.props.item.id;
      let token = JSON.parse(sessionStorage.getItem('token'));
      if(token){
        const config = {
            headers: {
               "Authorization" : 'Bearer '+token,
            },  
        }
        axios
        .get(url, config)
        .then((res) => {
          this.setState({ date: res.data.date, user : res.data.user, product : res.data.product});
        })
        .catch(err => this.setState({ message :  err.response.message}));
      }
   }
    render()
    {
        return (
            <article className="col1 flex column">
                <Link to={"/products/"+this.props.item.product.id}>
                    <div className="image">
                        {/* <img src={'http://localhost:8080/products/images/'+this.props.item.product.id} alt={this.props.title}></img> */}
                    
                <h3>{this.props.title}</h3>
                <i>{this.props.description}</i>
                <b>{this.props.cost}</b>
                <i>{this.state.date}</i>
                </div>
                    
                </Link>
                <i>{this.state.user.name+' '+this.state.user.email}</i>
                <br></br>
                {this.getAddRespond()}
                {this.getDeleteButton()}
                <div className="button">
                </div>
            </article>

    );
}
}

export default OrderItem;
