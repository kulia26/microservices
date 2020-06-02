import React from 'react';
import { Form, Field } from 'react-final-form'
const axios = require('axios');

const validateRecord = (values) => {
    var errors = {};
    if (!values.title ) {
      errors.title = "Пуста адреса";
    }
    if (!values.description ) {
      errors.description = "Пустий опис";
    }
    if (values.category==='' ) {
      errors.category = "Оберіть категорію";
    }
    if (!values.cost) {
      errors.cost = "Введіть ціну";
    }


    return errors;
}

class AddItem extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      user: sessionStorage.getItem('user'),
      message: 'Додайте новий товар', 
     };
    this.onSubmit = this.onSubmit.bind(this);
  }
  
  getCategories(){
    const categories = {
      'laptops': 'Ноутбуки',
      'tablets': 'Планшети',
      'smartphones': 'Смартфони',
      'keyboards': 'Клавіатури',
      'mouses': 'Мишки',
      'headphones': 'Навушники',
      'players': 'Плеєри',
    };
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

    const url = 'http://localhost:8080/product';
    let token = JSON.parse(sessionStorage.getItem('token'));
      const formData = {};
      console.log({values});
      formData.name = values.title;
      formData.description = values.description;
      formData.type = values.category;
      formData.price = values.cost;
      formData.rating = 1;

      const config = {
          headers: {
            "Content-Type":"application/json",
            "Authorization" : 'Bearer '+token,
          }
      }
      axios.post(url, formData,config)
      .then(res => this.setState({ message : 'Товар успішно додано'}))
      .catch(err => this.setState({ message :  err.response.data.message}));
    
  };
  render(){
    return(
    <>
    <div className="flex">
      <h1>Додати товар</h1>
    </div>
    <div className="flex">
      <h2>{this.state.message}</h2>
    </div>
      <section className="flex col5">
          <div>
            <Form
            onSubmit={this.onSubmit}
            validate={validateRecord}
            validateOnBlur={false}
            onChange={validateRecord}
            render={(props) => {
              return (
                <>
                  <form onSubmit={props.handleSubmit} className="flex column">
                    <Field name={`title`}>
                      {({ input, meta }) => (
                        <>
                          <label>Назва</label>
                          <input {...input} type="text" placeholder="назва товару" />
                          <p className="error">{meta.error}</p>
                        </>
                      )}
                    </Field>
                    <Field name={`description`}>
                      {({ input, meta }) => (
                        <>
                          <label>Опис</label>
                          <input {...input} type="text" placeholder="опис товару" />
                          <p className="error">{meta.error}</p>
                        </>
                      )}
                    </Field>
                    <Field name={`cost`}>
                      {({ input, meta }) => (
                        <>
                          <label>Ціна</label>
                          <input {...input} type="text" placeholder="ціна товару" />
                          <p className="error">{meta.error}</p>
                        </>
                      )}
                    </Field>
                    <label>Категорія</label>
                    <Field name="category" component="select">
                          <option />
                          {this.getCategories()}
                    </Field>
                    <div className="buttons">
                      <button type="submit" disabled={props.submitting || props.pristine}>
                        Додати
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

export default AddItem;
