import React from 'react';
import Card from './Card';
import axios from 'axios';
import categories from './categories';
class Cards extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      category: props.category, 
      isAdmin: props.isAdmin,
      title: props.title,
      user: props.user,
    };
    this.renderItems = this.renderItems.bind(this);
    this.someItemAreRemoved = this.someItemAreRemoved.bind(this);
    this.onEdit= this.onEdit.bind(this);   
  }

componentDidMount() {
  console.log(this.props.category);
  const categories = [
    'laptops',
    'tablets',
    'smartphones',
    'keyboards',
    'mouses',
    'headphones',
    'players'
  ];
    if(this.props.isSearch !== true){
      axios.get('http://localhost:8080/product/')
      .then(res => {
        let items  = res.data;
        console.log(items);
        if(this.props.category){
           items = items.filter(item=>{
             console.log(item);
             return (item.type === this.props.category)
           })
        }
        this.setState({items});
      })
      .catch(err=>{
        window.alert(err);
      })
    }else{
      const query = window.location.pathname.split('/').pop();
      console.log(query);
      axios.get('http://localhost:8080/product/find/'+ encodeURI(query))
      .then(res => {
        this.setState({items: res.data, title: "Результати пошуку за запитом: "+ query});
      })
      .catch(err=>{
        this.setState({title: "Помилка "+ err.response.message});
      })
    }
}

someItemAreRemoved(item){
  this.setState({items: this.state.items.filter(el=> el !== item), canMount: true});
}

onEdit(){
  this.props.onEdit();
}

renderItems(){
  let render = [];
    for (const key in this.state.items) {
        const el = this.state.items[key];
          render.push(<Card 
            isAdmin={this.props.isAdmin} 
            onEdit={this.onEdit} 
            onDelete={this.someItemAreRemoved} 
            item={el} 
            key={el.id}
            image={el.image}
            title={el.name}
            description={el.description} 
            cost={el.price}
            canBuy={this.props.user && this.props.user.role === "ROLE_USER"?true:false}/>)  
    }
  return render;
}

render()
  {return (
    <>
    <div className="flex">
      <h1>{this.state.title}</h1>
    </div>
    <section className="flex">
    {this.renderItems()}
    </section>
    </>
  );}
}

export default Cards;
