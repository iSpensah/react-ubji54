import React, { Component } from 'react';

class Employee extends React.Component {
  constructor() {
    super();
    this.state = { firstName: 'Spencer', title: 'Programmer' };
  }
  render() {
    return (
      <div>
        <h4> Hi my Name is: {this.state.firstName}</h4>
        <h3> Hi I am a {this.state.title} </h3>
      </div>
    );
  }
}
export default Employee;
