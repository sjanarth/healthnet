import React from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Login from "./components/Signin";
import SignUp from "./components/Signup";
import User from "./components/User";
import Users from "./components/Users";

function App() {
    return (
        <Router>
            <Switch>
                <Route exact path='/' component={Login} />
                <Route exact path="/signin" component={Login} />
                <Route exact path="/signup" component={SignUp} />
                <Route exact path="/user" component={User} />
            </Switch>
        </Router>
    );
}

export default App;