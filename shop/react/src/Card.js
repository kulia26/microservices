import React from 'react';
import { Link } from "react-router-dom";
const axios = require('axios');

class Card extends React.Component {
    constructor(props) {
        super(props);
        let user = sessionStorage.getItem('user');
        user = user ? JSON.parse(user) : {};
        this.state = {
          item: this.props.el,
          user: user
        };
        this.deleteThisItem = this.deleteThisItem.bind(this);
        this.editThisItem = this.editThisItem.bind(this);
        this.addToBasket = this.addToBasket.bind(this);
    }
    deleteThisItem() {
      const item = this.props.item;
      const url = 'http://localhost:8080/product/'+item.id;
      let token = JSON.parse(sessionStorage.getItem('token'));
      if(token){
        console.log({token});
        const config = {
            headers: {
               "Authorization" : token.tokenType+' '+token.accessToken,
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

     getDateNow = () =>{
      const date = new Date();
      const day = date.getDate() > 9 ? date.getDate() : '0'+date.getDate();
      const m = date.getMonth() + 1 > 9 ? date.getMonth() + 1 : '0'+(date.getMonth()+1);
      const time = date.toUTCString().slice(-12, -4)
      return `${m}/${day}/${date.getFullYear()} ${time}`;
     }

     addToBasket() {
      const item = this.props.item;
      const url = 'http://localhost:8080/booking';
      let token = JSON.parse(sessionStorage.getItem('token'));
      if(token){
        console.log({token});
        const config = {
            headers: {
               "Authorization" : 'Bearer '+token,
            },  
        }
        
        const orderItem = {
          userId:this.state.user.id,
          productId:item.id, 
          date: this.getDateNow()
        }
        axios
        .post(url, orderItem, config)
        .then((res) => {
          this.setState({ message : res.data.message});
        })
        .catch(err => this.setState({ message :  err.response.message}));
      }else{
        window.alert('Ви не авторизовані !');
      }
     }

     editThisItem() {
      sessionStorage.setItem('editing', JSON.stringify(this.props.item));
      this.props.onEdit();
     }

    getAdminButtons() {
        if(this.props.isAdmin){
          return(
            <>
            <div className="buttons">
              <div className="button">
              <button onClick={this.deleteThisItem}><span role="img" aria-label="emoji">🗑️</span>Видалити</button>
              </div>
              <div className="button">
              <Link to="/edititem">
               <button onClick={this.editThisItem}><span role="img" aria-label="emoji">✍️</span>Редагувати</button>
               </Link>
             </div>
            </div>       
            </>
          )
        }
    }

    renderMakeOrder() {
      if(this.props.canBuy){
        return(
          <>
          <button className="buy" onClick={this.addToBasket}>Купити</button>   
          </>
        )
      }
  }
    render()
    {
        return (
            <article className="col1 flex column">
                <Link to={"/products/"+this.props.item.id}>
                    <div className="image">
                        {/* <img src={'http://localhost:8080/products/images/'+this.props.item.id} alt={this.props.title}></img> */}
                    
                    <h3>{this.props.title}</h3>
                    </div>
                </Link>
                <i>{this.props.description}</i>
                <b>{this.props.cost}</b>
                <br></br>
                {this.getAdminButtons()}
                <div className="button">
                  {this.renderMakeOrder()}
                </div>
            </article>

    );
}
}

export default Card;
