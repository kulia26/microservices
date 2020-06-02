import React from 'react';
import { Link } from "react-router-dom";
import Respond from './Respond';
import categories from './categories'

const axios = require('axios');

class Item extends React.Component {
    constructor(props) {
        super(props);
        let user = sessionStorage.getItem('user');
        user = user ? JSON.parse(user) : {};
        this.state = {
          item: {
            id: '',
            image:'',
            name:'',
            description:'',
            type: '',
            feedback: [],
          }
        };
       
    }

    componentDidMount() {
      axios.get('http://localhost:8080/product/' + this.props.match.params.id )
      .then(res => {
        this.setState({item: res.data});
      }).then(res=>res.json())
      .catch((err, res)=>{
        this.setState({err:err.message})
      });

      axios.get('http://localhost:8080/feedback/product/' + this.props.match.params.id )
      .then(res => {
        this.setState({feedback: res.data});
      }).then(res=>res.json())
      .catch((err, res)=>{
        this.setState({err:err.message})
      });

    }

    

  getResponds() {
    if(this.state.feedback){
      const feedback = this.state.feedback;
     if(feedback && feedback.length){
      return feedback.map(f =>{
          return (<Respond product={this.state.item} key={f.id} feedback={f} ></Respond>)
      })
     }else{
      return (<p>Відгуків про товар не має</p>)
     }
    }
    
  }


    render()
    {
        return (
           <>
            <section className="flex">
                <div className="col1 flex item">

                    <div className="image col2">
                    <img src={`../assets/${this.state.item.type}.png`} alt={this.state.item.name}></img>
                    </div>
                    <div className="text col5">
                    <i>Категорія: {categories[this.state.item.type]|| ''}</i>
                        <h3>{this.state.item.name || ''}</h3>

                        <i>{this.state.item.description || ''}</i>
                        Рейтинг: <mark>{this.state.item.rating || ''}</mark>
                        
                    </div>
                    
                    
                </div>
            </section>
            <div className="flex">
                <h1>
                    Відгуки покупців
                </h1>
            </div>
            <section className="flex column">
            {this.getResponds()}
            </section>
           </>

        );

    }
}

export default Item;
