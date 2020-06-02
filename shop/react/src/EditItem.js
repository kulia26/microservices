import React from 'react';
import { Form, Field } from 'react-final-form'
import categories from './categories';
import { Link } from "react-router-dom";
const axios = require('axios');

const validateRecord = (values) => {
    var errors = {};
    if(values){
      if (!values.title ) {
        errors.title = "Пуста адреса";
      }
      if (!values.description ) {
        errors.description = "Пустий опис";
      }
      if (!values.category) {
        errors.category = "Оберіть категорію";
      }
        
      
      if (values.cost==='' ) {
        errors.category = "Ціна не може бути порожньою";
      }
    }
    

    return errors;
}



class EditItem extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      message: "",
      item: {

      }
    }
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentDidMount(){
   axios.get('http://localhost:8080/product/' + this.props.match.params.id )
      .then(res => {
        this.setState({item: res.data});
      }).then(res=>res.json())
      .catch((err, res)=>{
        this.setState({err:err.message})
      });
  }
  
  getCategories(){

    const tags = [];
    for (const key in categories ) {
      if (categories.hasOwnProperty(key)) {
        const category = categories[key];
        tags.push(<option key={key} value={key}>{category}</option>);
      }
    }   
    return tags;            
  }


  onSubmit(values) {
    const url = 'http://localhost:8080/product/'+this.state.item.id;
    let token = JSON.parse(sessionStorage.getItem('token'));
    if(token){
      const formData = {};
      
      formData.name= values.title;
      formData.description= values.description;
      formData.type= values.category;
      formData.price= values.cost;
      formData.rating = values.rating;
      
      console.log({values});
      const config = {
          headers: {
              "Content-Type":"application/json",
              "Authorization" : 'Bearer '+token,
          }
      }
      axios.put(url, formData, config)
      .then(res => this.setState({ message : 'Товар оновлено'}))
      .catch(err => this.setState({ message :  err.message}));
    }else{
      window.alert('Ви не авторизовані !');
    }
    
  };
  render(){
    return(
    <>
    <div className="flex">
      <h1>Редагувати</h1>
    </div>
    <div className="flex">
      <h2>{this.state.message}</h2>
    </div>
      <section className="flex col5">
          <div>
            <Form
            onSubmit={this.onSubmit}
            validate={validateRecord}
            validateOnBlur={true}
            onChange={validateRecord}
            render={(props) => {
              return (
                <>
                  <form onSubmit={props.handleSubmit} className="flex column">
                    <Field name={`title`} defaultValue={this.state.item.name}>
                      {({ input, meta }) => (
                        <>
                          <label>Назва</label>
                          <input {...input}  type="text" placeholder="назва товару" />
                          <p className="error">{meta.error}</p>
                        </>
                      )}
                    </Field>
                    <Field name={`description`}  defaultValue={this.state.item.description}>
                      {({ input, meta }) => (
                        <>
                          <label>Опис</label>
                          <input {...input} type="text" placeholder="опис товару" />
                          <p className="error">{meta.error}</p>
                        </>
                      )}
                    </Field>
                    <Field name={`cost`}  defaultValue={this.state.item.price}>
                      {({ input, meta }) => (
                        <>
                          <label>Ціна</label>
                          <input {...input} type="text" placeholder="ціна товару" />
                          <p className="error">{meta.error}</p>
                        </>
                      )}
                    </Field>

                    <Field name={`rating`}  defaultValue={this.state.item.rating}>
                      {({ input, meta }) => (
                        <>
                          <label>Рейтинг</label>
                          <input {...input} type="text" placeholder="рейтинг" />
                          <p className="error">{meta.error}</p>
                        </>
                      )}
                    </Field>
                    <label>Категорія</label>
                    <Field name="category" component="select"  defaultValue={this.state.item.type}>
                          {this.getCategories()}
                    </Field>
                    <div className="buttons">
                      <button type="submit" disabled={props.submitting || props.pristine}>
                        Зберегти
                      </button>
                      <button type="button" onClick={this.props.onDisable}>
                        <Link to="/">
                        Відмінити
                        </Link>
                      </button>
                    </div>
                  </form>
                </>
                )
            }}
          />
      </div>
    </section>
    </>
   )
}};

export default EditItem;
