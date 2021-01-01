import React from 'react';
import {Redirect, NavLink} from 'react-router-dom';

import '../assets/scss/style.scss';
//import Aux from "../../../hoc/_Aux";
//import Breadcrumb from "../../../App/layout/AdminLayout/Breadcrumb";
//import DEMO from "../../../store/constant";

class SignUp extends React.Component {
    constructor(props)  {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleSubmit(event) {
        event.preventDefault();
        /*
        fetch('http://localhost:8081/api/signup', {
            method: 'post',
            headers: {'Content-Type':'application/json'},
            body: JSON.stringify({
                "firstName": this.firstName.value,
                "lastName": this.lastName.value,
                "accountName": this.accountName.value,
                "email": this.email.value,
                "passWord": this.passWord.value
            })
        });
        */
       // return(<Redirect to="/users"/>);
       this.props.history.push('/user');
    }
    render () {
        return(
            <div className="auth-wrapper">
                <div className="auth-content">
                    <div className="auth-bg">
                        <span className="r"/>
                        <span className="r s"/>
                        <span className="r s"/>
                        <span className="r"/>
                    </div>
                    <form onSubmit={this.handleSubmit}>
                        <div className="card">
                            <div className="card-body text-center">
                                <div className="mb-4">
                                    <i className="feather icon-user-plus auth-icon"/>
                                </div>
                                <h3 className="mb-4">Sign up</h3>
                                <div className="input-group mb-3">
                                    <input type="text" ref={(ref) => {this.firstName=ref}} className="form-control" placeholder="Firstname"/>
                                </div>
                                <div className="input-group mb-3">
                                    <input type="text" ref={(ref) => {this.lastName=ref}} className="form-control" placeholder="Lastname"/>
                                </div>
                                <div className="input-group mb-3">
                                    <input type="text" ref={(ref) => {this.accountName=ref}} className="form-control" placeholder="Accountname"/>
                                </div>
                                <div className="input-group mb-3">
                                    <input type="email" ref={(ref) => {this.email=ref}} className="form-control" placeholder="Email"/>
                                </div>
                                <div className="input-group mb-4">
                                    <input type="password" ref={(ref) => {this.passWord=ref}} className="form-control" placeholder="Password"/>
                                </div>
                                <div className="form-group text-left">
                                    <div className="checkbox checkbox-fill d-inline">
                                        <input type="checkbox" name="checkbox-fill-2" id="checkbox-fill-2"/>
                                            <label htmlFor="checkbox-fill-2" className="cr">I accept the <a href="https://mit-license.org" target="_blank" rel="noopener noreferrer">terms of service</a>.</label>
                                    </div>
                                </div>
                                <button className="btn btn-primary shadow-2 mb-4" onClick={this.handleSubmit}>Sign up</button>
                                <p className="mb-0 text-muted">Already have an account? <NavLink to="/signin">Login</NavLink></p>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}

export default SignUp;
