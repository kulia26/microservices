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
          }
        };
        this.getAddRespond = this.getAddRespond.bind(this);
    }

    componentDidMount() {
      axios.get('http://localhost:8080/product/' + this.props.match.params.id )
      .then(res => {
        this.setState({item: res.data});
      }).then(res=>res.json())
      .catch((err, res)=>{
        this.setState({err:err.message})
      });

      //GET feedback about product 

    }

    getAddRespond() {
      if(this.props.login){
          return(
              <>
              <div className="buttons">
                <div className="button">
                <Link to={"/addRespond/"+this.props.match.params.id}>
                 <button >Додати відгук</button>
                 </Link>
               </div>
              </div>
              </>
            )
      }
  }

  getResponds() {
    if(this.state.item.feedback){
      const feedback = this.state.item.feedback;
     if(feedback && feedback.length){
      return feedback.map(res =>{
          return (<Respond text={res.text} image={res.image} name={res.name} ></Respond>)
      })
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
                        {/* category<img src={'http://localhost:8080/products/images/'+this.state.item.id} alt={this.state.item.name || ''}></img> */}
                    
                    <div className="text col5">
                    <i>Категорія: {categories[this.state.item.type]|| ''}</i>
                        <h3>{this.state.item.name || ''}</h3>

                        <i>{this.state.item.description || ''}</i>
                        
                    </div>
                    </div>
                    {this.getAddRespond()}
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
