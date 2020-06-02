import React from 'react';
import { Form, Field } from 'react-final-form';
import validator from 'validator';
const axios = require('axios');

class AddRespond extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      user: sessionStorage.getItem('user'),
     };
    this.onSubmit = this.onSubmit.bind(this);
  }

  getDateNow = () =>{
    const date = new Date();
    const day = date.getDate() > 9 ? date.getDate() : '0'+date.getDate();
    const m = date.getMonth() + 1 > 9 ? date.getMonth() + 1 : '0'+(date.getMonth()+1);
    const time = date.toUTCString().slice(-12, -4)
    return `${m}/${day}/${date.getFullYear()} ${time}`;
   }

   validateRecord = (values) => {
    var errors = {};
    if (!values.title ) {
      errors.title= "Пустий відгук";
    }

    if (!values.rating ) {
      errors.rating= "Пуста оцінка";
    }
    if (values.rating && (!validator.isAlphanumeric(values.rating+'') || values.rating > 10)) {
      errors.rating= "Введіть корректрну оцінку 0-10";
    }

    return errors;
}

  onSubmit(values) {
    const url = 'http://localhost:8080/feedback';
    let token = JSON.parse(sessionStorage.getItem('token'));
    if(token){
      const formData = {};
      formData.bookingId = this.props.match.params.id;
      console.log(formData.bookingId);
      formData.rating = values.rating;
      formData.text = values.title;
      formData.date = this.getDateNow();
      console.log({formData});
      
      const config = {
          headers: {
            "Content-Type":"application/json",
            "Authorization" : 'Bearer '+token,
          }
      }
      axios.post(url, formData,config)
      .then(res => this.setState({ message : 'Відгук успішно додано'}))
      .catch(err => this.setState({ message :  err.message}));
    }else{
      window.alert('Ви не авторизовані !');
    }
    
  };


  render(){
    return(
    <>
    <div className="flex">
      <h1>Додати відгук</h1>
    </div>
    <div className="flex">
      <h2>{this.state.message}</h2>
    </div>
      <section className="flex col5">
          <div>
            <Form
            onSubmit={this.onSubmit}
            validate={this.validateRecord}
            validateOnBlur={false}
            onChange={this.validateRecord}
            render={(props) => {
              return (
                <>
                  <form onSubmit={props.handleSubmit} className="flex column">
                    <Field name={`title`}>
                      {({ input, meta }) => (
                        <>
                          <label>Текст</label>
                          <input {...input} type="text" placeholder="опис товару" />
                          <p className="error">{meta.error}</p>
                        </>
                      )}
                    </Field>
                    <Field name={`rating`}>
                      {({ input, meta }) => (
                        <>
                          <label>Оцінка</label>
                          <input {...input} type="text" placeholder="оцінка" />
                          <p className="error">{meta.error}</p>
                        </>
                      )}
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

export default AddRespond;
