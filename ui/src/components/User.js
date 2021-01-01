import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import '../assets_adminty/css/style.css';
import React from 'react';
import { Tabs, Tab } from 'react-bootstrap';

class User extends React.Component {
    render() {
        return (
            <div className="auth-wrapper">
                <div className="auth-content-2">
                    <h3><b>Josephine Villa</b></h3>
                    <hr/>
                    <Tabs defaultActiveKey="home">
                        <Tab eventKey="home" title="Personal Info">
                            <div class="table-responsive">
                                <table class="table m-0">
                                    <tbody>
                                        <tr>
                                            <th scope="row">Full Name</th>
                                            <td>Josephine Villa</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Email</th>
                                            <td>joseph@example.com</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Website</th>
                                            <td><a href="http://www.google.com">www.google.com</a></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Mobile</th>
                                            <td>(800)-423-9878</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Location</th>
                                            <td>New York, USA</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Roles</th>
                                            <td>Tenant Group Admin</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                        </Tab>
                        <Tab eventKey="profile" title="Accounts">
                            <div class="col-md-6">
                                <div class="card b-l-success business-info services">
                                    <div class="card-header">
                                        <div class="service-header">
                                            <a href="#">
                                                <h5 class="card-header-text">HealthNet Development</h5>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="card-block">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <p class="task-detail">Lorem ipsum dolor sit amet</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br/>
                                <br/>
                                <div class="card b-l-success business-info services">
                                    <div class="card-header">
                                        <div class="service-header">
                                            <a href="#">
                                                <h5 class="card-header-text">HealthNet Production</h5>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="card-block">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <p class="task-detail">Lorem ipsum dolor sit amet, consectet </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </Tab>
                    </Tabs>
                </div>
            </div>
        );
    }
}

export default User;

